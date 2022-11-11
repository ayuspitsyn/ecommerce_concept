package com.example.ecommerceconcept.data.model

import com.example.ecommerceconcept.data.db.BestSellerItem
import com.example.ecommerceconcept.data.db.HomeStoreItem

data class MainJson (
    val hotSalesItems: Array<HomeStoreItem>,
    val mainListItems: Array<BestSellerItem>
    )