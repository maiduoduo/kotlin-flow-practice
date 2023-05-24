package com.vvitt.flowpractice.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vvitt.flowpractice.R
import com.vvitt.flowpractice.databinding.ActivityMainBinding
import com.vvitt.flowpractice.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val mBinding : FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
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
            btnFlowAndDownload.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_downloadFragment)
            }

            btnFlowAndRoom.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_userRoomFragment)
            }
            btnFlowAndRetrofit.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_musicNetFragment)
            }

            btnStateFlow.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_stateFlowFragment)
            }

            btnSharedFlow.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_sharedFlowFragment)
            }

            btnPaging.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_pagingFragment)
            }

            btnHilt.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_carFragment)
            }
        }
    }


}