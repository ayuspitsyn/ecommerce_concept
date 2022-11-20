package com.example.ecommerceconcept.domain.model.cart

import com.example.ecommerceconcept.domain.model.details.DetailsItemDomain
import java.text.NumberFormat

data class BasketItemDomain (
    val id: Int,
    val images: String,
    val price: Int,
    val title: String
    )

fun BasketItemDomain.formattedPrice():String {
    val f = NumberFormat.getCurrencyInstance()
    return f.format(price)
}