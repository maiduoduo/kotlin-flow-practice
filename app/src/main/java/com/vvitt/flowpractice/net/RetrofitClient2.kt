package com.vvitt.flowpractice.net

import android.util.Log
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.google.gson.GsonBuilder

import com.google.gson.Gson
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Flow
import kotlin.math.log

/**
 * @ClassName RetrofitClient
 * @author please call me police uncel
 * @since 2023/5/23.
 * @email 110
 * @Version: V1.0.0
 * @desciption Retrofit 管理类2
 **/
object RetrofitClient2 {
    ////http://m.kuaikanmanhua.com/search/mini/hot_word?&page=3&size=10
//    private val BASE_URL = "http://m.kuaikanmanhua.com/"

    private val gson: Gson? = GsonBuilder() //配置你的Gson
        .setDateFormat("yyyy-MM-dd hh:mm:ss")
        .create()

    fun getClient(baseUrl : String) : Retrofit{
        val instance : Retrofit by lazy {
            val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.e("vvitt", "interceptor: $it")

            })
            Retrofit.Builder()
                .client(OkHttpClient.Builder().addInterceptor {
                    it.proceed(it.request()).apply {
                        Log.e("vvitt", "request:${code()}")
                        Log.e("vvitt", "requesturl:${it.request().url()}")
                    }
                }.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        return instance
    }


   fun <T> createApi(clazz: Class<T>, baseUrl : String) : T{
       return getClient(baseUrl).create(clazz) as T
   }
}