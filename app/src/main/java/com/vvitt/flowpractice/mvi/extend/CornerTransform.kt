package com.vvitt.flowpractice.mvi.extend

import android.content.Context
import android.graphics.*
import androidx.annotation.NonNull
import androidx.annotation.Nullable

import com.bumptech.glide.load.resource.bitmap.BitmapResource

import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.Resource

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import java.security.MessageDigest


/**
 * @ClassName CornerTransform
 * @author please call me police uncel
 * @since 2023/6/9.
 * @email 110
 * @Version: V1.0.0
 * @desciption CornerTransform
 *
 * @param leftTop     左上角
 * @param rightTop    右上角
 * @param leftBottom  左下角
 * @param rightBottom 右下角
 **/
class CornerTransform(context: Context?,
                      var radius: Float,
                      var isLeftTop: Boolean = true,
                      var isRightTop: Boolean = true,
                      var isLeftBottom: Boolean = true,
                      var isRightBotoom: Boolean = true) : Transformation<Bitmap?> {
//    private var mBitmapPool: BitmapPool = Glide.get(context!!).bitmapPool
    private lateinit var mBitmapPool: BitmapPool

    private val id = javaClass.name
    private val idBytes = id.toByteArray(Charsets.UTF_8);

    /**
     * 需要设置圆角的部分
     *
     * @param leftTop     左上角
     * @param rightTop    右上角
     * @param leftBottom  左下角
     * @param rightBottom 右下角
     */
    private fun setNeedCorner(leftTop: Boolean, rightTop: Boolean, leftBottom: Boolean, rightBottom: Boolean) {
        isLeftTop = leftTop
        isRightTop = rightTop
        isLeftBottom = leftBottom
        isRightBotoom = rightBottom
    }

    override fun transform(context: Context, resource: Resource<Bitmap?>, outWidth: Int, outHeight: Int): Resource<Bitmap?> {
        val source: Bitmap = resource.get()
        var finalWidth: Int
        var finalHeight: Int
        //输出目标的宽高或高宽比例
        var scale: Float
        if (outWidth > outHeight) {
            //如果 输出宽度 > 输出高度 求高宽比
            scale = outHeight.toFloat() / outWidth.toFloat()
            finalWidth = source.width
            //固定原图宽度,求最终高度
            finalHeight = (source.width.toFloat() * scale).toInt()
            if (finalHeight > source.height) {
                //如果 求出的最终高度 > 原图高度 求宽高比
                scale = outWidth.toFloat() / outHeight.toFloat()
                finalHeight = source.height
                //固定原图高度,求最终宽度
                finalWidth = (source.height.toFloat() * scale).toInt()
            }
        } else if (outWidth < outHeight) {
            //如果 输出宽度 < 输出高度 求宽高比
            scale = outWidth.toFloat() / outHeight.toFloat()
            finalHeight = source.height
            //固定原图高度,求最终宽度
            finalWidth = (source.height.toFloat() * scale).toInt()
            if (finalWidth > source.width) {
                //如果 求出的最终宽度 > 原图宽度 求高宽比
                scale = outHeight.toFloat() / outWidth.toFloat()
                finalWidth = source.width
                finalHeight = (source.width.toFloat() * scale).toInt()
            }
        } else {
            //如果 输出宽度=输出高度
            finalHeight = source.height
            finalWidth = finalHeight
        }

        //修正圆角
        radius *= finalHeight.toFloat() / outHeight.toFloat()
        var outBitmap: Bitmap = mBitmapPool[finalWidth, finalHeight, Bitmap.Config.ARGB_8888]
        if (outBitmap == null) {
            outBitmap = Bitmap.createBitmap(finalWidth, finalHeight, Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(outBitmap)
        val paint = Paint()
        //关联画笔绘制的原图bitmap
        val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        //计算中心位置,进行偏移
        val width = (source.width - finalWidth) / 2
        val height = (source.height - finalHeight) / 2
        if (width != 0 || height != 0) {
            val matrix = Matrix()
            matrix.setTranslate((-width).toFloat(), (-height).toFloat())
            shader.setLocalMatrix(matrix)
        }
        paint.shader = shader
        paint.isAntiAlias = true
        val rectF = RectF(0.0f, 0.0f, canvas.width.toFloat(), canvas.height.toFloat())
        //先绘制圆角矩形
        canvas.drawRoundRect(rectF, radius, radius, paint)

        //左上角圆角
        if (!isLeftTop) {
            canvas.drawRect(0f, 0f, radius, radius, paint)
        }
        //右上角圆角
        if (!isRightTop) {
            canvas.drawRect(canvas.getWidth() - radius, 0f, canvas.width.toFloat(), radius, paint)
        }
        //左下角圆角
        if (!isLeftBottom) {
            canvas.drawRect(0f, canvas.height - radius, radius, canvas.height.toFloat(), paint)
        }
        //右下角圆角
        if (!isRightBotoom) {
            canvas.drawRect(
                canvas.width - radius,
                canvas.height - radius,
                canvas.width.toFloat(),
                canvas.height.toFloat(),
                paint
            )
        }

        return BitmapResource.obtain(outBitmap, mBitmapPool)!!

    }


//    override fun updateDiskCacheKey(messageDigest: MessageDigest) {}

    /** must override */
    override fun equals(other: Any?): Boolean {
        return other is CornerTransform
    }

    /** must override */
    override fun hashCode(): Int {
        return id.hashCode()
    }

    /** must override */
    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(idBytes)
    }

    /**
     * @param context 上下文
     * @param radius  圆角幅度
     */
    init {
        context?.let {
            mBitmapPool = Glide.get(it).bitmapPool
            this.radius = radius
        }
    }

}
