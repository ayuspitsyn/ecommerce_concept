package com.example.ecommerceconcept.data

import android.content.res.AssetManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.ecommerceconcept.data.db.EcommerceDao
import com.example.ecommerceconcept.data.db.asBestSellerItemDomain
import com.example.ecommerceconcept.data.db.asHomeStoreItemDomain
import com.example.ecommerceconcept.domain.Repository
import com.example.ecommerceconcept.domain.model.details.DetailsItemDomain
import com.example.ecommerceconcept.domain.model.home.HomeStoreItemDomain
import com.example.ecommerceconcept.data.model.MainJson
import com.example.ecommerceconcept.domain.model.home.BestSellerItemDomain
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class RepositoryImpl(
    private val ecommerceDao: EcommerceDao,
    private val assets: AssetManager
) : Repository {

    override suspend fun refreshRepo() = withContext(Dispatchers.Default) {
        val source: String? = getJsonFromAssets(HOME_SCREEN_LISTS_PATH)
        val type = object : TypeToken<MainJson>() {}.type
        val result: MainJson = Gson().fromJson(source, type)
        val hotSales = result.hotSalesItems.toList()
        ecommerceDao.insertHomeStoreDbItem(hotSales)
        val mainList = result.mainListItems.toList()
        ecommerceDao.insertBestSellerDbItem(mainList)
    }

    override suspend fun getHotSalesList(): LiveData<List<HomeStoreItemDomain>> {
        return Transformations.map(ecommerceDao.getHomeStoreDbItemList()) {
            it.asHomeStoreItemDomain()
        }
    }

    override suspend fun getBestSellerList(): LiveData<List<BestSellerItemDomain>> {
        return Transformations.map(ecommerceDao.getBestSellerDbItemsList()) {
            it.asBestSellerItemDomain()
        }
    }

    override suspend fun getItemDetails(itemId: Int): DetailsItemDomain = withContext(Dispatchers.Default) {
        val source: String? = getJsonFromAssets(DETAILS_PATH)
        val type = object : TypeToken<DetailsItemDomain>() {}.type
        return@withContext Gson().fromJson<DetailsItemDomain?>(source, type)
    }

    private fun getJsonFromAssets(jsonFileName: String): String? {
        val result: String
        try {
            result = assets.open(jsonFileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return result
    }

    companion object {
        const val HOME_SCREEN_LISTS_PATH = "main.json"
        const val DETAILS_PATH = "details.json"
    }

}