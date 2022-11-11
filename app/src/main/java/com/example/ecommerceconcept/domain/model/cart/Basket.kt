package com.example.ecommerceconcept.domain.model.cart

data class Basket (
    val items : List<BasketItem>,
    val delivery: String,
    val id: Int,
    val total: Int
    )