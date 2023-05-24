package com.vvitt.flowpractice.net

import com.vvitt.flowpractice.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @ClassName MovieApi
 * @author please call me police uncel
 * @since 2023/5/24.
 * @email 110
 * @Version: V1.0.0
 * @desciption MOVIE 数据请求的api
 **/
interface MovieApi {

    @GET("search/mini/hot_word")
    suspend fun getMovies(
        @Query("page") page:Int,
        @Query("size") size:Int
    ) : Movies
}