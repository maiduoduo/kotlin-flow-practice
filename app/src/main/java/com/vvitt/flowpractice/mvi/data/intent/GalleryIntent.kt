package com.vvitt.flowpractice.mvi.data.intent

/**
 * @ClassName GalleryIntent
 * @author please call me police uncel
 * @since 2023/6/9.
 * @email 110
 * @Version: V1.0.0
 * @desciption 数据行为类
 **/
sealed class GalleryIntent{
    /**
     * @desciption 获取壁纸
     * @since 2023/6/9
     **/
    object GetGallery : GalleryIntent()

}
