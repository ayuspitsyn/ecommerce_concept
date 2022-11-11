package com.example.ecommerceconcept.domain.model.cart

data class BasketDomain (
    val items : List<BasketItemDomain>,
    val delivery: String,
    val id: Int,
    val total: Int
    )