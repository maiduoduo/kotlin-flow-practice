package com.vvitt.flowpractice.mvi.data.state

import com.vvitt.flowpractice.mvi.data.MVIGalleryModel

/**
 * @ClassName GalleryState
 * @author please call me police uncel
 * @since 2023/6/9.
 * @email 110
 * @Version: V1.0.0
 * @desciption 数据状态
 **/
sealed class GalleryState{
    /**
     * @desciption 空闲
     * @since 2023/6/9
     **/
    object Idle : GalleryState()

    /**
     * @desciption 加载
     * @since 2023/6/9
     **/
    object Loading : GalleryState()

    /**
     * @desciption 获取壁纸
     * @since 2023/6/9
     **/
    data class Gallerys(val mviGalleryModel: MVIGalleryModel) : GalleryState()

    /**
     * @desciption 错误加载
     * @since 2023/6/9
     **/
    data class Error(val error: String) : GalleryState()
}
