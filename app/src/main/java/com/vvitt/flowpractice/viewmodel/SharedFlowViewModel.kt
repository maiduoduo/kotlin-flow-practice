package com.vvitt.flowpractice.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvitt.flowpractice.common.Event
import com.vvitt.flowpractice.common.LocalEventBus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @ClassName SharedFlowViewModel
 * @author please call me police uncel
 * @since 2023/5/24.
 * @email 110
 * @Version: V1.0.0
 * @desciption SHARE flow viewmodel
 **/
class SharedFlowViewModel : ViewModel() {

    private lateinit var job: Job
    fun startRefresh(){
        job = viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                LocalEventBus.postEvent(Event(System.currentTimeMillis()))
            }
        }

    }


    fun stopRefresh(){
        try {
            job.cancel()
        }catch (e : Throwable){
            e.printStackTrace()
        }

    }

}