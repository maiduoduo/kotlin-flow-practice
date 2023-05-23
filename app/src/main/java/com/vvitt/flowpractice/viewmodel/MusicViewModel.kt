package com.vvitt.flowpractice.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vvitt.flowpractice.model.Music
import com.vvitt.flowpractice.net.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * @ClassName MusicViewModel
 * @author please call me police uncel
 * @since 2023/5/23.
 * @email 110
 * @Version: V1.0.0
 * @desciption 音乐数据业务操作类
 **/
class MusicViewModel(app:Application) : AndroidViewModel(app) {

    val musics = MutableLiveData<Music>()


    fun searchMusic(mid:Int?, format: String) {
        viewModelScope.launch {
            flow {
                if (mid == null) {
                    RetrofitClient.musicApi.searchMusic(format).let {
                        Log.d("vvitt", "item data:  ${it.data?.name}")
                        emit(it)
                    }
                }else {
                    RetrofitClient.musicApi.searchMusic(mid, format).let {
                        emit(it)
                    }
                }

            }.flowOn(Dispatchers.IO)
                .catch { e -> e.printStackTrace() }
                .collect {
                    musics.value = it
                }
        }
    }


}