package com.example.ecommerceconcept.ui.homescreen.vm

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceconcept.data.RepositoryImpl
import com.example.ecommerceconcept.data.db.EcommerceDao

class HomeFragmentViewModelFactory(ecommerceDao: EcommerceDao, assets: AssetManager) :
    ViewModelProvider.Factory {

        private val repository by lazy {
            RepositoryImpl(ecommerceDao, assets)
        }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(repository) as T
    }
}