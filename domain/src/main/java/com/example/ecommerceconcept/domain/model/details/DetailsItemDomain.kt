package com.example.ecommerceconcept.domain.model.details

import java.text.NumberFormat

data class DetailsItemDomain(
    val CPU: String,
    val camera: String,
    val capacity: Array<String>,
    val color: Array<String>,
    val id: String,
    val images: Array<String>,
    val isFavorites: Boolean,
    val price: Int,
    val rating: Float,
    val sd: String,
    val ssd: String,
    val title: String,
)

fun DetailsItemDomain.formattedPrice(): String {
    val f = NumberFormat.getCurrencyInstance()
    return f.format(price)
}