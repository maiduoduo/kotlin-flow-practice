package com.vvitt.flowpractice.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.vvitt.flowpractice.adapter.MovieAdapter
import com.vvitt.flowpractice.adapter.MovieLoadMoreAdapter
import com.vvitt.flowpractice.databinding.FragmentPagingBinding
import com.vvitt.flowpractice.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.collectLatest


/**
 * @author please call me police uncel
 * @since 2023/5/24
 * @email 110
 * @desciption  jetpack Paging3 实践
 **/
class PagingFragment : Fragment() {

    private val viewmodel by viewModels<MovieViewModel>()

    private val mBinding : FragmentPagingBinding by lazy {
        FragmentPagingBinding.inflate(layoutInflater)
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

        context?.let { it ->
            val movieAdapter = MovieAdapter(it)
            mBinding.apply {
                rvPaging.adapter = movieAdapter
                    .withLoadStateFooter(MovieLoadMoreAdapter(it))
                srfresh.setOnRefreshListener {
                    movieAdapter.refresh()
                }
            }
            lifecycleScope.launchWhenCreated {
                viewmodel.loadMovie().collectLatest { pagingData ->
                    movieAdapter.submitData(pagingData)
                }
            }

            lifecycleScope.launchWhenCreated {
                movieAdapter.loadStateFlow.collectLatest { state ->
                    mBinding.srfresh.isRefreshing = state.refresh is LoadState.Loading
                }
            }
        }




    }
}