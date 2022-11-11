package com.example.ecommerceconcept.domain.model.cart

data class BasketItemDomain (
    val id: Int,
    val images: String,
    val price: Int,
    val title: String
    )