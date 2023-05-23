package com.vvitt.flowpractice.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.vvitt.flowpractice.R
import com.vvitt.flowpractice.adapter.UserAdapter
import com.vvitt.flowpractice.databinding.FragmentUserRoomBinding
import com.vvitt.flowpractice.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collect
import androidx.recyclerview.widget.OrientationHelper

import androidx.recyclerview.widget.StaggeredGridLayoutManager

import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager





/**
 * A simple [Fragment] subclass.
 * Use the [UserRoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 * Flow 与 Room 结合使用的实例
 */
class UserRoomFragment : Fragment() {


    private val viewModel by viewModels<UserViewModel>()

    private val mBinding : FragmentUserRoomBinding by lazy {
        FragmentUserRoomBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            val adapter = UserAdapter(it)
            //这里用线性显示 类似于listview
            mBinding.apply {
                btnAdduser.setOnClickListener {
                    viewModel.insert(
                        etId.text.toString(),
                        etFirstname.text.toString(),
                        etLastname.text.toString()
                    )

                }

                recyclerview.apply {
                    layoutManager = LinearLayoutManager(it)
                    //这里用线性宫格显示 类似于gridview
                    //layoutManager(GridLayoutManager(this, 2))
                    //这里用线性宫格显示 类似于瀑布流
                    /* layoutManager(StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL))*/
                    this.adapter = adapter
                }
            }

            lifecycleScope.launchWhenCreated {
                viewModel.getAll().collect { value ->
                    adapter.setData(value)
                    mBinding.recyclerview.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }

    }



}