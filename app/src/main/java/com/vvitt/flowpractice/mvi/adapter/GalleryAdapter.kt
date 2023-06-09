package com.vvitt.flowpractice.mvi.adapter

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vvitt.flowpractice.adapter.BindingViewHolder
import com.vvitt.flowpractice.databinding.ItemGalleryRvBinding
import com.vvitt.flowpractice.mvi.data.Vertical
import com.bumptech.glide.load.resource.bitmap.CenterCrop

import com.bumptech.glide.request.RequestOptions
import com.vvitt.flowpractice.mvi.extend.RoundedCornersTransform


/**
 * @ClassName GalleryAdapter
 * @author please call me police uncel
 * @since 2023/6/9.
 * @email 110
 * @Version: V1.0.0
 * @desciption Gallery 列表适配器
 **/
class GalleryAdapter(private val context: Context,private val verticals: ArrayList<Vertical>) : RecyclerView.Adapter<BindingViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemGalleryRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        verticals?.let {
            val data = it[position]
            val itemBinding = holder.binding as ItemGalleryRvBinding
            //加载图片
            data.preview.let { url ->
                if (!TextUtils.isEmpty(url)){
//                    Picasso.get().load(url).placeholder(R.mipmap.ic_launcher_round)
//                        .into(itemBinding.ivItemGallery)


                    // 加载为四个都是圆角的图片 可以设置圆角幅度
//                    val options =
//                        RequestOptions.bitmapTransform(RoundedCorners(DipPx.dip2px(context, 3.0f)))
//
//                    Glide.with(holder.itemView.context).asBitmap()
//                        .load(url)
//                        .placeholder(R.mipmap.ic_launcher_round)
////                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//避免圆角黑边
//                        .apply(options)
//                        .into(itemBinding.ivItemGallery)


                    //默认裁剪四个圆角，不需要设置圆角，对应参数设为false
                    Glide.with(context)
                        .load(url)
                        .apply(RequestOptions().transform(
                                CenterCrop(), RoundedCornersTransform(
                                    context, 20.0f,
                                    leftBottom = true,
                                    rightBottom = true
                                )
                            )
                        )
//                        .placeholder(R.mipmap.ic_launcher_round)
                        .into(itemBinding.ivItemGallery)

                }else{
                    itemBinding.ivItemGallery.setBackgroundColor(Color.DKGRAY)
                }
            }
        }
    }

    override fun getItemCount(): Int = verticals?.size

    fun setData(data: List<Vertical>){
        this.verticals.clear()
        verticals.addAll(data)
        notifyDataSetChanged()
    }

}