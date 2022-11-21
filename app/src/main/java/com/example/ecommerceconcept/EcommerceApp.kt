package com.example.ecommerceconcept

import android.app.Application
import com.example.ecommerceconcept.data.db.EcommerceDb

class EcommerceApp : Application() {
    val database: EcommerceDb by lazy { EcommerceDb.getDatabase(this) }
}