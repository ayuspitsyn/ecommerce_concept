package com.example.ecommerceconcept.domain

import androidx.lifecycle.LiveData
import com.example.ecommerceconcept.domain.model.details.DetailsItemDomain
import com.example.ecommerceconcept.domain.model.home.BestSellerItemDomain
import com.example.ecommerceconcept.domain.model.home.HomeStoreItemDomain

interface Repository {

    suspend fun refreshRepo()

    fun getHotSalesList(): LiveData<List<HomeStoreItemDomain>>

    fun getBestSellerList(): LiveData<List<BestSellerItemDomain>>

    fun getItemDetails(itemId: Int): LiveData<DetailsItemDomain>

}