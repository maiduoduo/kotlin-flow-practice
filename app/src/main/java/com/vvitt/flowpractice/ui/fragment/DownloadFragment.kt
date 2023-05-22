package com.vvitt.flowpractice.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vvitt.flowpractice.R
import com.vvitt.flowpractice.databinding.FragmentDownloadBinding
import com.vvitt.flowpractice.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DownloadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DownloadFragment : Fragment() {

    private val mBinding : FragmentDownloadBinding by lazy {
        FragmentDownloadBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding.root
    }


}