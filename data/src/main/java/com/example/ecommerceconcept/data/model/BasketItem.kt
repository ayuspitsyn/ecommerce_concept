package com.example.ecommerceconcept.data.model

import com.example.ecommerceconcept.domain.model.cart.BasketItemDomain

data class BasketItem(
    val id: Int,
    val images: String,
    val price: Int,
    val title: String
)

fun BasketItem.asBasketItemDomain(): BasketItemDomain {
    return BasketItemDomain(
        id = this.id,
        images = this.images,
        price = this.price,
        title = this.title
    )
}