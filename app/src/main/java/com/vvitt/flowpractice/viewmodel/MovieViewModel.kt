package com.vvitt.flowpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vvitt.flowpractice.common.AppCommon.Companion.PAGING_INITIAL_PAGE_SIZE
import com.vvitt.flowpractice.common.AppCommon.Companion.PAGING_PAGE_SIZE
import com.vvitt.flowpractice.model.Movie
import com.vvitt.flowpractice.paging3.MoviePagingSource
import kotlinx.coroutines.flow.Flow


/**
 * @ClassName MovieViewModel
 * @author please call me police uncel
 * @since 2023/5/24.
 * @email 110
 * @Version: V1.0.0
 * @desciption MOVIE 的 ViewModel
 **/
class MovieViewModel : ViewModel(){

    private val movies by lazy {
        Pager(
            config = PagingConfig(
                pageSize = PAGING_PAGE_SIZE,
                // 滑动到 item位置加载更多
                prefetchDistance = 1,
                initialLoadSize= PAGING_INITIAL_PAGE_SIZE
            ),
            pagingSourceFactory = {MoviePagingSource()}
        ).flow.cachedIn(viewModelScope)
    }

    fun loadMovie() : Flow<PagingData<Movie>> = movies

}