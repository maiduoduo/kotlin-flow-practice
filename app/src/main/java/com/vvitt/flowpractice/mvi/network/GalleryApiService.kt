package com.vvitt.flowpractice.mvi.network

import com.vvitt.flowpractice.mvi.data.MVIGalleryModel
import retrofit2.http.GET

/**
 * @ClassName GalleryApiService
 * @author please call me police uncel
 * @since 2023/6/9.
 * @email 110
 * @Version: V1.0.0
 * @desciption 画廊的网络Api
 **/
interface GalleryApiService {

    /**
     * 获取壁纸
     */
    @GET("v1/vertical/vertical?limit=30&skip=180&adult=false&first=0&order=hot")
    suspend fun getGallery() : MVIGalleryModel
}