package com.vvitt.flowpractice.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.vvitt.flowpractice.R
import com.vvitt.flowpractice.databinding.FragmentSharedFlowBinding
import com.vvitt.flowpractice.viewmodel.SharedFlowViewModel


/**
 * @author please call me police uncel
 * @since 2023/5/23
 * @email 110
 * @desciption SharedFlow
 **/
class SharedFlowFragment : Fragment() {
    private val viewModel by viewModels<SharedFlowViewModel>()

    private val mBinding : FragmentSharedFlowBinding by lazy {
        FragmentSharedFlowBinding.inflate(layoutInflater)
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
        mBinding.apply {
            fbtnStart.setOnClickListener(){
                viewModel.startRefresh()
            }
            fbtnStop.setOnClickListener(){
                viewModel.stopRefresh()
            }
        }


    }


    override fun onPause() {
        super.onPause()
//        viewModel.stopRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stopRefresh()
    }
}