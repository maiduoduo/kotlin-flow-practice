package com.vvitt.flowpractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.vvitt.flowpractice.databinding.ItemMovieLoadmoreBinding

/**
 * @ClassName MovieLoadMoreAdapter
 * @author please call me police uncel
 * @since 2023/5/24.
 * @email 110
 * @Version: V1.0.0
 * @desciption movie 加载更多的适配器
 **/
class MovieLoadMoreAdapter(private val context: Context) : LoadStateAdapter<BindingViewHolder>(){

    override fun onBindViewHolder(holder: BindingViewHolder, loadState: LoadState) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BindingViewHolder {
        val mBinding = ItemMovieLoadmoreBinding.inflate(LayoutInflater.from(context),parent,false)
        return BindingViewHolder(mBinding)
    }
}