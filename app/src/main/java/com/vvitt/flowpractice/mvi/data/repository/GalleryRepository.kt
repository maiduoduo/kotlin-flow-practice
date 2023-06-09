package com.vvitt.flowpractice.mvi.data.repository

import com.vvitt.flowpractice.mvi.cons.CommonApi.GALLERY_BASE_URL
import com.vvitt.flowpractice.mvi.network.GalleryApiService
import com.vvitt.flowpractice.mvi.network.GalleryRetrofitClient

/**
 * @ClassName GalleryRepository
 * @author please call me police uncel
 * @since 2023/6/9.
 * @email 110
 * @Version: V1.0.0
 * @desciption 数据存储仓库
 **/
class GalleryRepository {


    //创建service实例
    private var netWork = GalleryRetrofitClient.createApi(
        GalleryApiService::class.java, baseUrl = GALLERY_BASE_URL
    )

    /**
     * @desciption 获取壁纸
     * @since 2023/6/9
     **/
    suspend fun getGallery() = netWork.getGallery()

}