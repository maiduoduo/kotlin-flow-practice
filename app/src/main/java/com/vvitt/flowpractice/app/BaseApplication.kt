/*
 *
 *  * Copyright (C)  HuangLinqing, TravelPrevention Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.vvitt.flowpractice.app

import android.app.Application
import android.content.Context


/**
 * @author please call me police uncel
 * @since 2023/6/9
 * @email 110
 * @desciption application
 **/
class BaseApplication : Application() {

    companion object {
//        lateinit var context: Context
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this

    }
}