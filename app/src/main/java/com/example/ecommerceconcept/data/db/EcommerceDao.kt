package com.example.ecommerceconcept.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EcommerceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHomeStoreDbItem(list: List<HomeStoreItem>)

    @Query("SELECT * FROM home_store")
    fun getHomeStoreDbItemList(): LiveData<List<HomeStoreItem>>




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBestSellerDbItem(list: List<BestSellerItem>)

    @Query("SELECT * FROM best_seller")
    fun getBestSellerDbItemsList(): LiveData<List<BestSellerItem>>

}