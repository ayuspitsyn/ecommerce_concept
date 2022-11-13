package com.example.ecommerceconcept.domain.model.details

data class DetailsItemDomain (
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