package com.example.ecommerceconcept.domain.model.home

data class HomeStoreItemDomain (
    val id: Int,
    val is_new: Boolean,
    val title: String,
    val subtitle: String,
    val picture: String,
    val is_buy: Boolean
    )