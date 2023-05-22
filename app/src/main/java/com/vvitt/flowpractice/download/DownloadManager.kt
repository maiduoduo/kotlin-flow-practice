package com.vvitt.flowpractice.download

import com.vvitt.flowpractice.utils.copyTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException

/**
 * @ClassName DownloadManager
 * @author please call me police uncel
 * @since 2023/5/22.
 * @email 110
 * @Version: V1.0.0
 * @desciption 下载管理器
 **/
object DownloadManager {
    fun download(url : String, file : File) : Flow<DownloadStatus> {
        return flow {
            val request = Request.Builder().url(url).get().build()
            val response = OkHttpClient.Builder().build().newCall(request).execute()
            if (response.isSuccessful){
                // ！！ 强制执行异常
                response.body()!!.let { body ->
                    val total = body.contentLength()
                    //文件读写
                    //use 自动关闭流
                    file.outputStream().use {output->
                        val input = body.byteStream()
                        var emittedProgress = 0L
                        input.copyTo(output){bytesCopied->
                            val progress = bytesCopied * 100 / total
                            if (progress - emittedProgress > 5){
                                emit(DownloadStatus.Progress(progress.toInt()))
                                emittedProgress = progress
                            }

                        }
                    }

                }
                emit(DownloadStatus.Done(file))
            }else{
                throw IOException(response.toString())
            }

        }.catch{
            file.delete()
            emit(DownloadStatus.Error(it))
        }.flowOn(Dispatchers.IO)
    }
}