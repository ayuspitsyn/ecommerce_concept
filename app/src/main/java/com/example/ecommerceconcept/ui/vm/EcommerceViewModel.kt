package com.example.ecommerceconcept.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceconcept.domain.Repository
import com.example.ecommerceconcept.domain.model.cart.BasketDomain
import com.example.ecommerceconcept.domain.model.details.DetailsItemDomain
import com.example.ecommerceconcept.domain.model.home.BestSellerItemDomain
import com.example.ecommerceconcept.domain.model.home.HomeStoreItemDomain
import kotlinx.coroutines.launch

class EcommerceViewModel(private val repository: Repository) : ViewModel() {

    var isReady = false

    init {
        isReady = false
        viewModelScope.launch {
            repository.refreshRepo()
            isReady = true
        }
    }

    val hotSales: LiveData<List<HomeStoreItemDomain>> = repository.getHotSalesList()

    val bestSeller: LiveData<List<BestSellerItemDomain>> = repository.getBestSellerList()

    fun getItemDetails(id: Int): LiveData<DetailsItemDomain> = repository.getItemDetails(id)

    val cart: LiveData<BasketDomain> = repository.getBasket()

}