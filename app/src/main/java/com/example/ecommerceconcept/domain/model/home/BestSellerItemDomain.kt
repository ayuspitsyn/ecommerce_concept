package com.example.ecommerceconcept.domain.model.home

data class BestSellerItemDomain (
    val id: Int,
    val is_favorites: Boolean,
    val title: String,
    val price_without_discount: Int,
    val discount_price: Int,
    val picture: String
    )