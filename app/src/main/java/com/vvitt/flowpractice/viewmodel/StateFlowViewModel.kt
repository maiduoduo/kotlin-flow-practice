package com.vvitt.flowpractice.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * @ClassName StateFlowViewModel
 * @author please call me police uncel
 * @since 2023/5/23.
 * @email 110
 * @Version: V1.0.0
 * @desciption STATEFLOW VIEWMODEL
 **/
class StateFlowViewModel : ViewModel(){

    val number = MutableStateFlow(0)

    fun increment(){
        number.value++
    }

    fun decrement(){
        number.value--
    }

}