package com.vvitt.flowpractice.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.vvitt.flowpractice.R
import com.vvitt.flowpractice.common.LocalEventBus
import com.vvitt.flowpractice.databinding.FragmentSharedFlowBinding
import com.vvitt.flowpractice.databinding.FragmentTextBinding
import kotlinx.coroutines.flow.collect


/**
 * A simple [Fragment] subclass.
 * Use the [TextFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TextFragment : Fragment() {

    private val mBinding : FragmentTextBinding by lazy {
        FragmentTextBinding.inflate(layoutInflater)
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

        lifecycleScope.launchWhenCreated {
            Log.e("vvitt", "collect 1")
            LocalEventBus.events.collect(){value ->
                Log.e("vvitt", "collect 2")
                mBinding.tvTime.text = value.timestamp.toString()
            }

        }


    }
}