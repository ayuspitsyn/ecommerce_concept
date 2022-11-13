package com.example.ecommerceconcept.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerceconcept.domain.model.home.HomeStoreItemDomain
import com.example.ecommerceconcept.domain.model.home.BestSellerItemDomain

@Entity(tableName = "home_store")
data class HomeStoreItem (
    @PrimaryKey val id: Int,
    @ColumnInfo val is_new: Boolean?,
    @ColumnInfo val title: String,
    @ColumnInfo val subtitle: String,
    @ColumnInfo val picture: String,
    @ColumnInfo val is_buy: Boolean
)

fun List<HomeStoreItem>.asHomeStoreItemDomain(): List<HomeStoreItemDomain> {
    return map {
        HomeStoreItemDomain (
            id = it.id,
            is_new = it.is_new ?: false,
            title = it.title,
            subtitle = it.subtitle,
            picture = it.picture,
            is_buy = it.is_buy
        )
    }
}

@Entity(tableName = "best_seller")
data class BestSellerItem (
    @PrimaryKey val id: Int,
    @ColumnInfo val is_favorites: Boolean,
    @ColumnInfo val title: String,
    @ColumnInfo val price_without_discount: Int,
    @ColumnInfo val discount_price: Int,
    @ColumnInfo val picture: String
)

fun List<BestSellerItem>.asBestSellerItemDomain(): List<BestSellerItemDomain> {
    return map {
        BestSellerItemDomain (
            id = it.id,
            is_favorites = it.is_favorites,
            title = it.title,
            price_without_discount = it.price_without_discount,
            discount_price = it.discount_price,
            picture = it.picture
        )
    }
}