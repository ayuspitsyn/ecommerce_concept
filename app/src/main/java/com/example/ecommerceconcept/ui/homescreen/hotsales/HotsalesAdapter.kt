package com.example.ecommerceconcept.ui.homescreen.hotsales

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ecommerceconcept.domain.model.home.HomeStoreItemDomain

class HotsalesAdapter(fragment: Fragment, val items: List<HomeStoreItemDomain>) : FragmentStateAdapter (fragment) {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {

        val fragment = HotsalesFragment()
        fragment.arguments = Bundle().apply {
            putInt(ID, items[position].id)
            putBoolean(IS_NEW, items[position].is_new)
            putString(TITLE, items[position].title)
            putString(SUBTITLE, items[position].subtitle)
            putString(PICTURE, items[position].picture)
            putBoolean(IS_BUY, items[position].is_buy)
        }
        return fragment
    }

    companion object{
        const val ID = "id"
        const val ID_DEFAULT = -1
        const val IS_NEW = "is_new"
        const val IS_NEW_DEFAULT = false
        const val TITLE = "title"
        const val TITLE_DEFAULT = ""
        const val SUBTITLE = "subtitle"
        const val SUBTITLE_DEFAULT = ""
        const val PICTURE = "picture"
        const val PICTURE_DEFAULT = ""
        const val IS_BUY = "is_buy"
        const val IS_BUY_DEFAULT = false
    }
}