
package com.vvitt.flowpractice.mvi.extend

import com.vvitt.flowpractice.mvi.view.LoadingDialog


/**
 * @author please call me police uncel
 * @since 2023/6/9
 * @email 110
 * @desciption LoadingDialog 的扩展函数
 **/

/**
 * 弹窗默认样式
 */
fun LoadingDialog.Builder.defaultDialogView(): LoadingDialog.Builder {
    setDialogSize(
        120f, 112f
    )
    return this
}
