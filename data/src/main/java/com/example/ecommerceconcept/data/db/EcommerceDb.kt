package com.example.ecommerceconcept.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database

@Database(
    entities = arrayOf(
        HomeStoreItem::class,
        BestSellerItem::class
    ), version = 1
)
abstract class EcommerceDb : RoomDatabase() {

    abstract fun ecommerceDao(): EcommerceDao

    companion object {
        private lateinit var INSTANCE: EcommerceDb
        fun getDatabase(context: Context): EcommerceDb {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        EcommerceDb::class.java,
                        "ecommerce_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}