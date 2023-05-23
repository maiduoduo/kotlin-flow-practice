package com.vvitt.flowpractice.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.vvitt.flowpractice.R
import com.vvitt.flowpractice.databinding.FragmentStateFlowBinding
import com.vvitt.flowpractice.viewmodel.StateFlowViewModel
import kotlinx.coroutines.flow.collect

/**
 * @author please call me police uncel
 * @since 2023/5/23
 * @email 110
 * @desciption stateflow示例
 **/
class StateFlowFragment : Fragment() {
    private val viewModel by viewModels<StateFlowViewModel>()

    private val mBinding : FragmentStateFlowBinding by lazy {
        FragmentStateFlowBinding.inflate(layoutInflater)
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
            btnPlus.setOnClickListener {
                viewModel.increment()
            }

            btnSubtract.setOnClickListener {
                viewModel.decrement()
            }

        }

        lifecycleScope.launchWhenCreated {
            viewModel.number.collect{ value ->
                mBinding.tvResult.text = value.toString()
            }
        }
    }


}