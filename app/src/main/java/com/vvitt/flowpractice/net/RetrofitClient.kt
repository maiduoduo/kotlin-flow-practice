package com.vvitt.flowpractice.net

import android.util.Log
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.google.gson.GsonBuilder

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Flow

/**
 * @ClassName RetrofitClient
 * @author please call me police uncel
 * @since 2023/5/23.
 * @email 110
 * @Version: V1.0.0
 * @desciption Retrofit 管理类
 **/
object RetrofitClient {
    private val BASE_URL = "https://api.uomg.com/"

    private val gson: Gson? = GsonBuilder() //配置你的Gson
        .setDateFormat("yyyy-MM-dd hh:mm:ss")
        .create()

    private val instance : Retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor {
                it.proceed(it.request()).apply {
                    Log.e("vvitt", "request:${code()}")
                    Log.e("vvitt", "requesturl:${it.request().url()}")
                }
            }.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val musicApi : MusicApi by lazy {
        instance.create(MusicApi::class.java)
    }
}