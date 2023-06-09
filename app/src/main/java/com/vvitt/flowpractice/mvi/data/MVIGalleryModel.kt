package com.vvitt.flowpractice.mvi.data


import com.squareup.moshi.Json

data class MVIGalleryModel(
    @Json(name = "code")
    val code: Int,
    @Json(name = "msg")
    val msg: String,
    @Json(name = "res")
    val res: Res
)