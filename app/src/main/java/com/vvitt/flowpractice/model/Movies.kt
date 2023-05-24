package com.vvitt.flowpractice.model

import com.google.gson.annotations.SerializedName

/**
 * @ClassName Movies
 * @author please call me police uncel
 * @since 2023/5/24.
 * @email 110
 * @Version: V1.0.0
 * @desciption paging3 使用请求的数据实体
 **/
//http://m.kuaikanmanhua.com/search/mini/hot_word?&page=3&size=10
//data class Movies(val code:Int,val data: Movie? = null)
data class Movies(
    val code:Int,
    val message:String,
    val request_id:String,
    @SerializedName("total")
    val total:Int,
    @SerializedName("hits")
    val hits:Hits,
)

data class Hits(
    val guide_text:String,
    @SerializedName("hot_word")
    val hotWord: List<Movie>

)


/*data class Movie(
    val target_title:String,
    val action_type:Int,
    val target_id:Int,
    val target_str:String,
    val hot_word_source:String,
    val description:String,
    val background_image_url:String,
    val heat_count:Int
)*/
