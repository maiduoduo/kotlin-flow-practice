package com.vvitt.flowpractice.download

import java.io.File

/**
 * @ClassName DownloadStatus
 * @author please call me police uncel
 * @since 2023/5/22.
 * @email 110
 * @Version: V1.0.0
 * @desciption 下载状态管理 密封类
 **/
sealed class DownloadStatus{
    object None : DownloadStatus()
    data class Progress(val value:Int) : DownloadStatus()
    data class Error(val throwable: Throwable) : DownloadStatus()
    data class Done(val file:File) : DownloadStatus()

}
