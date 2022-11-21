package com.example.ecommerceconcept.domain.model.cart

import java.text.NumberFormat

data class BasketDomain(
    val items: List<BasketItemDomain>,
    val delivery: String,
    val id: Int,
    val total: Int
)

fun BasketDomain.getFormattedTotal(): String {
    val f = NumberFormat.getCurrencyInstance()
    f.maximumFractionDigits = 0
    return f.format(total)
}