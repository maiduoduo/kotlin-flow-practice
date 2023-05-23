package com.vvitt.flowpractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vvitt.flowpractice.databinding.ItemUserBinding
import com.vvitt.flowpractice.db.User

/**
 * @ClassName UserAdapter
 * @author please call me police uncel
 * @since 2023/5/23.
 * @email 110
 * @Version: V1.0.0
 * @desciption 用户信息列表适配器
 **/
class UserAdapter(private val context: Context) : RecyclerView.Adapter<BindingViewHolder>(){
    private val data = ArrayList<User>()

    fun setData(data: List<User>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = data[position]
        val itemUserBinding = holder.binding as ItemUserBinding
        itemUserBinding.tvUser.text = "${item.uid}, ${item.firstName} ,${item.lastName}"

    }

    override fun getItemCount(): Int {
        return data?.let {
            data.size
        }?:0
    }
}