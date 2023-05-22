package com.vvitt.flowpractice.utils

import java.io.InputStream
import java.io.OutputStream

/**
 * @ClassName IOExt
 * @author please call me police uncel
 * @since 2023/5/22.
 * @email 110
 * @Version: V1.0.0
 * @desciption 流的扩展函数：copyTo,覆盖父类中的方法
 **/

inline fun InputStream.copyTo(out:OutputStream,bufferSize : Int = DEFAULT_BUFFER_SIZE,
progress : (Long) -> Unit): Long {
    var bytesCopied: Long = 0
    val buffer = ByteArray(bufferSize)
    var bytes = read(buffer)
    while (bytes >= 0) {
        out.write(buffer, 0, bytes)
        bytesCopied += bytes
        bytes = read(buffer)

        progress(bytesCopied)
    }
    return bytesCopied

}