package com.example.ecommerceconcept.data

import android.content.res.AssetManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.example.ecommerceconcept.data.db.EcommerceDao
import com.example.ecommerceconcept.data.db.asBestSellerItemDomain
import com.example.ecommerceconcept.data.db.asHomeStoreItemDomain
import com.example.ecommerceconcept.data.model.*
import com.example.ecommerceconcept.domain.Repository
import com.example.ecommerceconcept.domain.model.details.DetailsItemDomain
import com.example.ecommerceconcept.domain.model.home.HomeStoreItemDomain
import com.example.ecommerceconcept.domain.model.cart.BasketDomain
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
        val hotSales = result.home_store
        ecommerceDao.insertHomeStoreDbItem(hotSales)
        val mainList = result.best_seller
        ecommerceDao.insertBestSellerDbItem(mainList)
    }

    override fun getHotSalesList(): LiveData<List<HomeStoreItemDomain>> {
        return Transformations.map(ecommerceDao.getHomeStoreDbItemList()) {
            it.asHomeStoreItemDomain()
        }
    }

    override fun getBestSellerList(): LiveData<List<BestSellerItemDomain>> {
        return Transformations.map(ecommerceDao.getBestSellerDbItemsList()) {
            it.asBestSellerItemDomain()
        }
    }

    override fun getItemDetails(itemId: Int): LiveData<DetailsItemDomain> = liveData {
        val source: String? = getJsonFromAssets(DETAILS_PATH)
        val type = object : TypeToken<DetailsItem>() {}.type
        emit(Gson().fromJson<DetailsItem?>(source, type).asDetailsItemDomain())
    }

    override fun getBasket(): LiveData<BasketDomain> = liveData {
        val source: String? = getJsonFromAssets(BASKET_PATH)
        val type = object : TypeToken<Basket>() {}.type
        emit(Gson().fromJson<Basket?>(source, type).asBasketDomain())
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
        const val HOME_SCREEN_LISTS_PATH = "backendEmulated/main.json"
        const val DETAILS_PATH = "backendEmulated/details.json"
        const val BASKET_PATH = "backendEmulated/basket.json"
    }

}