
package com.vvitt.flowpractice.mvi.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue


/**
 * @author please call me police uncel
 * @since 2023/6/9
 * @email 110
 * @desciption 设备相关工具类
 **/
object DeviceUtil {

    /**
     * dp转为px
     * @param context  上下文
     * @param dipValue dp值
     * @return
     */
    fun dip2px(context: Context, dipValue: Float): Int {
        val r: Resources = context.applicationContext.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dipValue, r.displayMetrics
        ).toInt()
    }
}