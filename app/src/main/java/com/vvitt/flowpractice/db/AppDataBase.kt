package com.vvitt.flowpractice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.coroutines.Continuation

/**
 * @ClassName AppDataBase
 * @author please call me police uncel
 * @since 2023/5/23.
 * @email 110
 * @Version: V1.0.0
 * @desciption Room本地数据库的database工具类
 **/
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDataBase :RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object{
        private var instance: AppDataBase?= null

        fun getInstance (context: Context) : AppDataBase{
            return instance?: synchronized(this){
                Room.databaseBuilder(context,AppDataBase::class.java,"vvitt_flow_practice.db")
                    .build().also {
                        instance = it
                    }
            }
        }
    }


}