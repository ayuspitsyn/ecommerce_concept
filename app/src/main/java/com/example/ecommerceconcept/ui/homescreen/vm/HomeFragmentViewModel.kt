package com.example.ecommerceconcept.ui.homescreen.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceconcept.domain.Repository
import com.example.ecommerceconcept.domain.model.home.BestSellerItemDomain
import com.example.ecommerceconcept.domain.model.home.HomeStoreItemDomain
import kotlinx.coroutines.launch

class HomeFragmentViewModel(repository: Repository): ViewModel() {

    lateinit var hotSales: LiveData<List<HomeStoreItemDomain>>
    lateinit var bestSeller: LiveData<List<BestSellerItemDomain>>

    init{
        viewModelScope.launch {
            repository.refreshRepo()
            hotSales=repository.getHotSalesList()
            bestSeller=repository.getBestSellerList()
        }
    }

}