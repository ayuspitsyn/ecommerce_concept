package com.example.ecommerceconcept.domain.model.home

import java.text.NumberFormat

data class BestSellerItemDomain(
    val id: Int,
    val is_favorites: Boolean,
    val title: String,
    val price_without_discount: Int,
    val discount_price: Int,
    val picture: String
)

fun BestSellerItemDomain.formattedPriceWithoutDiscount(): String {
    val f = NumberFormat.getCurrencyInstance()
    f.maximumFractionDigits = 0
    return f.format(price_without_discount)
}

fun BestSellerItemDomain.formattedDiscountPrice(): String {
    val f = NumberFormat.getCurrencyInstance()
    f.maximumFractionDigits = 0
    return f.format(discount_price) + " us"
}