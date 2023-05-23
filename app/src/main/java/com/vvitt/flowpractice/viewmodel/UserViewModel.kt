package com.vvitt.flowpractice.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvitt.flowpractice.db.AppDataBase
import com.vvitt.flowpractice.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * @ClassName UserViewModel
 * @author please call me police uncel
 * @since 2023/5/23.
 * @email 110
 * @Version: V1.0.0
 * @desciption user viewmodel 业务处理
 **/
class UserViewModel(app:Application) : AndroidViewModel(app){

    /**
     * @desciption 插入数据
     * @since 2023/5/23
     **/
    fun insert(uid : String,firstName : String, lastName : String){
        viewModelScope.launch {
            if (uid.isBlank() && firstName.isBlank() && lastName.isBlank()){
                return@launch
            }
            AppDataBase.getInstance(getApplication())
                .userDao()
                .insert(User(uid?.toInt(),firstName,lastName))
            Log.d("vvitt", "insert: $uid")
        }
    }

    /**
     * @desciption 查询所有
     * @since 2023/5/23
     **/
    fun getAll() : Flow<List<User>> {
        return AppDataBase.getInstance(getApplication())
            .userDao()
            .getAll()
            .catch { e -> e.printStackTrace() }
            .flowOn(Dispatchers.IO)
    }


}