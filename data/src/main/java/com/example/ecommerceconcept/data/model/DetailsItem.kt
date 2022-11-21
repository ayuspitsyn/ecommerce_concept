package com.example.ecommerceconcept.data.model

import com.example.ecommerceconcept.domain.model.details.DetailsItemDomain

data class DetailsItem(
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

fun DetailsItem.asDetailsItemDomain(): DetailsItemDomain {
    return DetailsItemDomain(
        CPU = this.CPU,
        camera = this.camera,
        capacity = this.capacity,
        color = this.color,
        id = this.id,
        images = this.images,
        isFavorites = this.isFavorites,
        price = this.price,
        rating = this.rating,
        sd = this.sd,
        ssd = this.ssd,
        title = this.title,
    )
}