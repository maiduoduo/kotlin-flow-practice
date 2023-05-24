package com.vvitt.flowpractice.paging3

import android.util.Log
import androidx.paging.PagingSource
import com.vvitt.flowpractice.model.Movie
import com.vvitt.flowpractice.net.MovieApi
import com.vvitt.flowpractice.net.RetrofitClient2
import java.lang.Error

/**
 * @ClassName MoviePagingSource
 * @author please call me police uncel
 * @since 2023/5/24.
 * @email 110
 * @Version: V1.0.0
 * @desciption Movie数据的 paging source配置类
 **/
class MoviePagingSource : PagingSource<Int,Movie>(){

    //currentPage , pageSize
    //1,8
    //2,8
    //3,8

    //prevKey , nextKey
    //null,2
    //1,3
    //2,4
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val currentPage = params.key ?: 1
        //TODO: 这里写死的， params.loadSize 运行无效
        val pageSize = 8
//        val pageSize = params.loadSize
        Log.e("vvitt", "loadSize:  $pageSize" )
        val movies = RetrofitClient2.createApi(MovieApi::class.java)
            .getMovies(currentPage,pageSize)

        val prevKey : Int? = null
        val nextKey : Int? = null


        prevKey == if (currentPage == 1)null else currentPage -1
        nextKey == if (currentPage <  ((movies.total)/(pageSize))) currentPage+1 else null

        return try {
            LoadResult.Page(
                data = movies.hits.hotWord,
                prevKey = prevKey,
                nextKey = nextKey

            )
        }catch (e : Throwable){
            LoadResult.Error(e)
        }

    }
}

