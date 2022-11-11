package com.example.ecommerceconcept.domain

import androidx.lifecycle.LiveData
import com.example.ecommerceconcept.domain.model.details.DetailsItemDomain
import com.example.ecommerceconcept.domain.model.home.BestSellerItemDomain
import com.example.ecommerceconcept.domain.model.home.HomeStoreItemDomain

interface Repository {

    suspend fun refreshRepo()

    suspend fun getHotSalesList(): LiveData<List<HomeStoreItemDomain>>

    suspend fun getBestSellerList(): LiveData<List<BestSellerItemDomain>>

    suspend fun getItemDetails(): DetailsItemDomain

}