package com.example.ecommerceconcept.data.model

import com.example.ecommerceconcept.domain.model.cart.BasketDomain

data class Basket (
    val items : List<BasketItem>,
    val delivery: String,
    val id: Int,
    val total: Int
    )

fun Basket.asBasketDomain(): BasketDomain {
    return BasketDomain(
        items = this.items.map {it.asBasketItemDomain()},
        delivery = this.delivery,
        id = this.id,
        total = this.total
    )
}