package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.data.repository.FakeUserpostRepository
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import kotlinx.coroutines.runBlocking
import org.junit.Before

class GetAllUserPostsUseCaseTest {

    private lateinit var getAllUserPostsUseCase: GetAllUserPostsUseCase
    private lateinit var fakeUserPostRepository: FakeUserpostRepository

    @Before
    fun setup() {
        fakeUserPostRepository = FakeUserpostRepository()
        getAllUserPostsUseCase = GetAllUserPostsUseCase(repository = fakeUserPostRepository)

        // FakeRepositoryに追加する用にDummyListを作成
        val dummyPost = mutableListOf<UserPostItem>()
        ('a'..'z').forEachIndexed { index, c ->
            dummyPost.add(
                UserPostItem(
                    body = c.toString(),
                    id = index,
                    title = c.toString(),
                    userId = index
                )
            )
        }
        dummyPost.shuffle()

        // dummyデータをここでFakeRepositoryのリストに追加
        runBlocking {
            dummyPost.forEach {
                fakeUserPostRepository.postUserPost(it)
            }
        }
    }
}