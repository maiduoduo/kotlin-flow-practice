package com.vvitt.flowpractice.adapter

import android.graphics.Color
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.vvitt.flowpractice.R

/**
 * @ClassName ImageviewBindingAdapter
 * @author please call me police uncel
 * @since 2023/5/24.
 * @email 110
 * @Version: V1.0.0
 * @desciption imageview 加载图片的适配器
 **/
class ImageviewBindingAdapter {
    //TODO : 必须使用 静态 才能Java 调用kotlin
    companion object{
        @JvmStatic
        @BindingAdapter("imagesrc")
        fun setImageView(imageview:ImageView,url:String){
            if (!TextUtils.isEmpty(url)){
                Picasso.get().load(url).placeholder(R.mipmap.ic_launcher_round)
                    .into(imageview)
            }else{
                imageview.setBackgroundColor(Color.DKGRAY)
            }
        }
    }

}