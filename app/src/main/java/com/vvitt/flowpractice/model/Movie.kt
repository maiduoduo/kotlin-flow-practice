package com.vvitt.flowpractice.model

/**
 * @author please call me police uncel
 * @since 2023/5/24
 * @email 110
 * @desciption  Movie实体类
 **/
data class Movie(
    val target_title:String,
    val action_type:Int,
    val target_id:Int,
    val target_str:String,
    val hot_word_source:String,
    val description:String,
    val background_image_url:String,
    val heat_count:Int
)