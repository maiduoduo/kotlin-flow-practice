package com.vvitt.flowpractice.net

import com.vvitt.flowpractice.model.Music
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @ClassName MusicApi
 * @author please call me police uncel
 * @since 2023/5/23.
 * @email 110
 * @Version: V1.0.0
 * @desciption 网络请求 的API 管理类
 **/
interface MusicApi {

    @GET("api/comments.163")
    suspend fun searchMusic(
        @Query("format") format : String
    ) : Music

    @GET("api/comments.163")
    suspend fun searchMusic(
        @Query("mid") mid : Int,
        @Query("format") format : String
    ) : Music

}