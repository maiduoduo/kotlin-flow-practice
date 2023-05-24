package com.vvitt.flowpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
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

    fun loadMovie() : Flow<PagingData<Movie>> {
        return Pager(
//            PagingConfig(8),
            config = PagingConfig(
                pageSize = 8
            ),
            pagingSourceFactory = {MoviePagingSource()}
        ).flow
    }

}