package com.vvitt.flowpractice.mvi.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vvitt.flowpractice.mvi.data.repository.GalleryRepository
import com.vvitt.flowpractice.mvi.network.HttpErrorDeal
import java.lang.IllegalArgumentException

/**
 * @ClassName ViewmodelFactory
 * @author please call me police uncel
 * @since 2023/6/9.
 * @email 110
 * @Version: V1.0.0
 * @desciption viewmodel 工厂
 **/
class ViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((GalleryViewModel::class.java))){
            return GalleryViewModel(GalleryRepository()) as T
        }
        HttpErrorDeal.dealHttpError(IllegalArgumentException("Unknown class"))
        throw IllegalArgumentException("Unknown class")
    }

}