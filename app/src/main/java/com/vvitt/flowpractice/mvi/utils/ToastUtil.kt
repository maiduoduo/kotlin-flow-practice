
package com.vvitt.flowpractice.mvi.utils

import android.widget.Toast
import com.vvitt.flowpractice.app.BaseApplication


/**
 * @author please call me police uncel
 * @since 2023/6/9
 * @email 110
 * @desciption toast 工具类
 **/
object ToastUtil {

    /**
     * 短提示
     * @param message 提示语
     */
    fun shortShow(message: String) {
        Toast.makeText(
            BaseApplication.context,
            message, Toast.LENGTH_SHORT
        ).show()
    }

    /**
     * 长提示
     * @param message 提示语
     */
    fun longShow(message: String) {
        Toast.makeText(
            BaseApplication.context,
            message, Toast.LENGTH_LONG
        ).show()
    }

}