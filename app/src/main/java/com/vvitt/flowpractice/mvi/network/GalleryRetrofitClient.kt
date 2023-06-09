package com.vvitt.flowpractice.mvi.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @ClassName GalleryRetrofitClient
 * @author please call me police uncel
 * @since 2023/6/9.
 * @email 110
 * @Version: V1.0.0
 * @desciption Retrofit的构建类
 **/
object GalleryRetrofitClient {


    /**
     * @desciption 通过Moshi 将Json 转化为 Kotlin 的 data class
     * @since 2023/6/9
     **/
    private val moshi:Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * @desciption 构建Retrofit
     * @since 2023/6/9
     **/
    private fun getRetrofit(baseUrl:String)  = Retrofit.Builder()
        .client(OkHttpClient.Builder().addInterceptor {
            it.proceed(it.request()).apply {
                Log.d("vvitt", "request:${code()}")
                Log.d("vvitt", "requesturl:${it.request().url()}")
            }
        }.build())
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    /**
     * 构建访问的API Service
     */
    fun <T> createApi(clazz: Class<T>, baseUrl: String) : T{
        return getRetrofit(baseUrl).create(clazz) as T
    }



}