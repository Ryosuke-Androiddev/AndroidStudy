package com.example.androidestudy.feature.retrofit.data.remote

import com.example.androidestudy.feature.retrofit.data.remote.dto.UserPostDto
import com.example.androidestudy.feature.retrofit.data.remote.dto.UserPostItemDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UserPostApi {

    // JSONレスポンスが入れ子になってるからここも同様にListで返却する
    @GET("/posts")
    suspend fun getUserPosts(): List<UserPostItemDto>

    // userIdではなく、idで取得する
    // Stringに変換してIdを渡す
    @GET("/posts/{id}")
    suspend fun getPostById(
        @Path("id") id: String
    ): UserPostItemDto

    // 一旦ドメインモデルでもらって、実行時にmapperで型変換する
    // ドメインが知らなくていい型変換の情報を持っていることはよくない??
    // POSTの時は特に戻り値を設ける必要がない??
    // オブジェクトをそのまま使うことはできない?? → RequestBodyに変更する
    // 引数は、JSON(文字列)に変更しなくても良い??
    @POST("/posts")
    suspend fun postUserPost(@Body userPostItemDto: UserPostItemDto)

    // 部分的なリソースの変更はPATCHで行う
    @PATCH("/posts/{id}")
    suspend fun updatePost(@Path("id") id: String)

    @DELETE("/posts/{id}")
    suspend fun deletePost(@Path("id") id: String)

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}