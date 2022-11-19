package com.example.ecommerceconcept.ui.detailsscreen.detailspager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class DetailsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return itemDetailsTabs.size
    }

    override fun createFragment(position: Int): Fragment {

        return when (position){
            SHOP -> DetailsFragmentPagerItem()
            DETAILS -> DetailsFragmentPagerItem()
            FEATURES -> DetailsFragmentPagerItem()
            else -> DetailsFragmentPagerItem()
        }
    }

    companion object{
        val itemDetailsTabs = arrayOf("Shop", "Details", "Features")
        const val SHOP = 0
        const val DETAILS = 1
        const val FEATURES = 2
    }
}

