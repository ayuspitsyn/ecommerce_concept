package com.example.ecommerceconcept.data.model

import com.example.ecommerceconcept.data.db.BestSellerItem
import com.example.ecommerceconcept.data.db.HomeStoreItem

data class MainJson (
    val home_store: List<HomeStoreItem>,
    val best_seller: List<BestSellerItem>
    )