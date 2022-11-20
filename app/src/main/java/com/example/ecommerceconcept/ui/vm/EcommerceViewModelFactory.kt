package com.example.ecommerceconcept.ui.vm

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceconcept.data.RepositoryImpl
import com.example.ecommerceconcept.data.db.EcommerceDao

class EcommerceViewModelFactory(ecommerceDao: EcommerceDao, assets: AssetManager) :
    ViewModelProvider.Factory {

    private val repository by lazy {
        RepositoryImpl(ecommerceDao, assets)
    }

    private lateinit var instance: EcommerceViewModel

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        instance = if(::instance.isInitialized) instance else EcommerceViewModel(repository)
        return instance as T
    }
}