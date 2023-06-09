package com.vvitt.flowpractice.mvi.data


import com.squareup.moshi.Json

data class Res(
    @Json(name = "vertical")
    val vertical: List<Vertical>
)