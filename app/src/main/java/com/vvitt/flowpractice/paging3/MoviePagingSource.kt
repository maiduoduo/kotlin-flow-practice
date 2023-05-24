package com.vvitt.flowpractice.paging3

import android.util.Log
import androidx.paging.PagingSource
import com.vvitt.flowpractice.common.AppCommon.Companion.PAGING_INITIAL_PAGE_SIZE
import com.vvitt.flowpractice.common.AppCommon.Companion.PAGING_PAGE_SIZE
import com.vvitt.flowpractice.model.Movie
import com.vvitt.flowpractice.net.MovieApi
import com.vvitt.flowpractice.net.RetrofitClient2
import kotlinx.coroutines.delay


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
        //为了展示上拉刷新（加载更多）效果
        delay(1200)
        val currentPage = params.key?: 1
        val pageSize = params.loadSize
        val movies = RetrofitClient2.createApi(MovieApi::class.java)
            .getMovies(currentPage,pageSize)

        var prevKey : Int? = null
        var nextKey : Int? = null
        var totalpage = (movies.total)/(pageSize)
        val realPageSize = PAGING_PAGE_SIZE
        val initialLoadSize = PAGING_INITIAL_PAGE_SIZE

        Log.e("vvitt", "currentpage: $currentPage  ,  totalpage:  ${totalpage}, pageSize : $pageSize" )
//        prevKey = if (currentPage == 1)null else currentPage -1
//        nextKey = if (currentPage <  (movies.total)/(pageSize)) currentPage+1 else null

        //以防数据错乱
        if (currentPage == 1){
            prevKey = null
            nextKey = initialLoadSize / realPageSize + 1
        }else{
            prevKey = currentPage - 1
            nextKey = if (currentPage <  (movies.total)/(pageSize)) currentPage+1 else null
        }
        Log.e("vvitt", "prevKey:$prevKey,nextKey:$nextKey")


        return try {
            LoadResult.Page(
                data = movies.hits.hotWord,
                prevKey = prevKey,
                nextKey = nextKey

            )
        }catch (e : Throwable){
            e.printStackTrace()
            LoadResult.Error(e)
        }

    }
}

