package com.example.ecommerceconcept.data.model

import com.example.ecommerceconcept.domain.model.cart.BasketDomain

data class Basket (
    val basket : List<BasketItem>,
    val delivery: String,
    val id: Int,
    val total: Int
    )

fun Basket.asBasketDomain(): BasketDomain {
    return BasketDomain(
        items = this.basket.map {it.asBasketItemDomain()},
        delivery = this.delivery,
        id = this.id,
        total = this.total
    )
}