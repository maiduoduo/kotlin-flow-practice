
package com.vvitt.flowpractice.mvi.utils

import android.content.Context
import com.vvitt.flowpractice.mvi.extend.defaultDialogView
import com.vvitt.flowpractice.mvi.view.LoadingDialog


/**
 * @author please call me police uncel
 * @since 2023/6/9
 * @email 110
 * @desciption 加载对话框工具类
 **/
object DialogLoadingUtils {

    private var dialog: LoadingDialog? = null

    /**
     * 显示等待框
     */
    fun showLoading(context: Context, mes: String) {

        dialog?.apply {
            if (isShowing) {
                cancel()
            }
        }

        dialog = LoadingDialog.Builder(context)
            .defaultDialogView()
            .setBootomDesc(mes)
            .create()
        dialog!!.show()
    }


    /**
     * 取消
     */
    fun cancel() {
        dialog?.cancel()
    }
}