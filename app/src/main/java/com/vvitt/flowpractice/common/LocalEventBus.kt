package com.vvitt.flowpractice.common

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.isActive
import kotlin.reflect.KProperty

/**
 * @ClassName LocalEventBus
 * @author please call me police uncel
 * @since 2023/5/24.
 * @email 110
 * @Version: V1.0.0
 * @desciption 模拟数据总线
 **/
object LocalEventBus {
    val  events = MutableSharedFlow<Event>()
    suspend fun postEvent(event: Event){
        events.emit(event)
        delay(100)
    }
}


data class Event(val timestamp: Long)

