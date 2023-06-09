package com.vvitt.flowpractice.mvi.utils

import android.content.Context

/**
 * @ClassName DipPx
 * @author please call me police uncel
 * @since 2023/6/9.
 * @email 110
 * @Version: V1.0.0
 * @desciption  提供px和dip的相互转化
 **/
object DipPx {
    fun dip2px(context: Context, dpValue: Float) : Int{
         val scale = context.resources.displayMetrics.density;
        return (dpValue * scale + 0.5f).toInt()
    }


    fun px2dip(context: Context, pxValue: Float) : Int{
        val scale = context.resources.displayMetrics.density;
        return (pxValue / scale + 0.5f).toInt()
    }
}