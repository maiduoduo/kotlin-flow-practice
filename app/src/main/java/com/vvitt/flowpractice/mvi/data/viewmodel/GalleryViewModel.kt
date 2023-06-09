package com.vvitt.flowpractice.mvi.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvitt.flowpractice.mvi.data.intent.GalleryIntent
import com.vvitt.flowpractice.mvi.data.repository.GalleryRepository
import com.vvitt.flowpractice.mvi.data.state.GalleryState
import com.vvitt.flowpractice.mvi.network.HttpErrorDeal
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * @ClassName GalleryViewModel
 * @author please call me police uncel
 * @since 2023/6/9.
 * @email 110
 * @Version: V1.0.0
 * @desciption 数据业务处理
 *
 * @link MviFragment
 **/
class GalleryViewModel(private val repository: GalleryRepository) : ViewModel() {

    /**
     * 创建intent 管道， 容量无限大
     */
    val mainIntentChannel = Channel<GalleryIntent>(Channel.UNLIMITED)

    //可变状态数据流
    private val _state = MutableStateFlow<GalleryState>(GalleryState.Idle)

    //不可变可观察状态数据流
    val stateflow : StateFlow<GalleryState>
                get() = _state

    init {
        viewModelScope.launch {
            //收集行为意图
            mainIntentChannel.consumeAsFlow().collect {
                when(it){
                    //匹配意图
                    is GalleryIntent.GetGallery -> getGalleryInCurrent()
                }
            }
        }

    }

    /**
     * @desciption 获取壁纸数据
     * @since 2023/6/9
     **/
    private fun getGalleryInCurrent() {
        viewModelScope.launch {
            //修改状态： 加载中
            _state.value = GalleryState.Loading
            //网络请求状态
            _state.value = try {
                //TODO: 网络加载较快，这里延迟一下
                delay(1000)
                //请求成功
                GalleryState.Gallerys(repository.getGallery())

            }catch(e : Exception){
                HttpErrorDeal.dealHttpError(e)
                //请求失败
                GalleryState.Error(e.localizedMessage ?: "Unknown Error")

            }

        }
    }


}