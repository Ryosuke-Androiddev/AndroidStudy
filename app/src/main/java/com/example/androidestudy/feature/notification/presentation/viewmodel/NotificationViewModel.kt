package com.example.androidestudy.feature.notification.presentation.viewmodel

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.di.module.DefaultNotificationCompatBuilder
import com.example.androidestudy.di.module.SecondNotificationCompatBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    @DefaultNotificationCompatBuilder
    private val notificationBuilder: NotificationCompat.Builder,
    @SecondNotificationCompatBuilder
    private val secondNotificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat
): ViewModel() {

    fun displaySimpleNotification() {
        notificationManager.notify(1, notificationBuilder.build())
    }

    fun updateSimpleNotification() {
        notificationManager.notify(1, notificationBuilder
            .setContentTitle("Updated Title")
            .build()
        )
    }

    fun cancelSimpleNotification() {
        notificationManager.cancel(1)
    }

    fun showProgress() {
        val limit = 10
        var count = 0
        viewModelScope.launch {
            while (count != limit) {
                delay(1500)
                count++
                notificationManager.notify(
                    3,
                    secondNotificationBuilder
                        .setContentTitle("Downloading")
                        .setContentText("${count}/${limit}")
                        .setProgress(limit, count, false).build()
                )
            }
            notificationManager.notify(
                3,
                notificationBuilder
                    .setContentTitle("Done")
                    .setContentText("")
                    .setContentIntent(null)
                    .clearActions()
                    .setProgress(0, 0, false).build()
            )
        }
    }
}