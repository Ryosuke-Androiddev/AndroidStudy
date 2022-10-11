package com.example.androidestudy.feature.retrofit.test_rule

import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.newSingleThreadContext
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@DelicateCoroutinesApi
class ComposeStateTestRule(
    snapshotIntervalMilliSec: Long = 1L
) : TestWatcher() {

    // スレッド指定(Coroutine Context)
    private val dispatcher = newSingleThreadContext(name = "snapshot")
    // Coroutine Scopeの追加、Coroutine Contextの追加
    // SuperVisorJob → 子Coroutineで例外が発生しても親Coroutineまで例外を伝播しない
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)
    private var job: Job? = null

    private val snapshotTaker = flow<Unit> {
        coroutineScope {
            while (isActive) {
                delay(snapshotIntervalMilliSec)
                Snapshot.takeSnapshot { }
            }
        }
    }

    // isActiveな時に、Snapshotを取る処理をジョブとして起動する
    override fun starting(description: Description) {
        job = snapshotTaker.launchIn(scope)
    }

    // Testが終わるごとに初期化処理
    override fun finished(description: Description) {
        job?.cancel()
        job = null
    }
}