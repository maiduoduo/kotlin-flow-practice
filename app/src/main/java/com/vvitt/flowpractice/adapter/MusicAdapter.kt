package com.vvitt.flowpractice.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vvitt.flowpractice.databinding.ItemMusicBinding
import com.vvitt.flowpractice.databinding.ItemUserBinding
import com.vvitt.flowpractice.db.User
import com.vvitt.flowpractice.model.Music

/**
 * @ClassName UserAdapter
 * @author please call me police uncel
 * @since 2023/5/23.
 * @email 110
 * @Version: V1.0.0
 * @desciption 音乐信息列表适配器
 **/
class MusicAdapter(private val context: Context) : RecyclerView.Adapter<BindingViewHolder>(){
    private val data = ArrayList<Music>()

    fun setData(data: List<Music>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = data[position]
        val itemBinding = holder.binding as ItemMusicBinding
        itemBinding.itemTvMusic.text = "${(item.data)?.name},  ${item.data}"

    }

    override fun getItemCount(): Int  = data.size
}