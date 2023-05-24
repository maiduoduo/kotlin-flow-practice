package com.vvitt.flowpractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.vvitt.flowpractice.databinding.ItemMovieBinding
import com.vvitt.flowpractice.model.Movie

/**
 * @ClassName MovieAdapter
 * @author please call me police uncel
 * @since 2023/5/23.
 * @email 110
 * @Version: V1.0.0
 *  TODO: 分页 需要 使用 PagingDataAdapter
 * @desciption MOVIE 信息列表适配器
 **/
class MovieAdapter(private val context: Context) :
    PagingDataAdapter<Movie,BindingViewHolder>(object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.target_id == newItem.target_id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            // kotlin  == 比较内容 ， === 比较引用
            //java  == 比较引用 ， equals 比较内容
            return oldItem == newItem
        }


    }){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }
    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = getItem(position)
        val itemBinding = holder.binding as ItemMovieBinding
        item?.let {
//            itemBinding.tvItemMovie.text =  "${it.target_title},  ${it.hot_word_source}, ${it.background_image_url}"
            itemBinding.movie = it
            itemBinding.networkImage = it.background_image_url
//            itemBinding.tvItemTargetId.text = "${it.target_id}"
            itemBinding.netTargetId = "${it.target_id}"


        }

    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int,payloads: MutableList<Any>) {
        val item = getItem(position)
        val itemBinding = holder.binding as ItemMovieBinding
        item?.let {
            itemBinding.movie = it
            itemBinding.networkImage = it.background_image_url
//            itemBinding.tvItemTargetId.text = "${it.target_id}"
            itemBinding.netTargetId = "${it.target_id}"
        }

    }



}






        /*

 private val data = ArrayList<Movie>()

    fun setData(data: List<Movie>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
             val binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
             return BindingViewHolder(binding)
         }

         override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
             val item = data[position]
             val itemBinding = holder.binding as ItemMovieBinding
             itemBinding.tvItemMovie.text = "${(item.target_title)},  ${item.hot_word_source}, ${item.background_image_url}"

         }

         override fun getItemCount(): Int  = data.size*/
