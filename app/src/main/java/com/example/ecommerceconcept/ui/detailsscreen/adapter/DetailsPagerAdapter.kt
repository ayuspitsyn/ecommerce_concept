package com.example.ecommerceconcept.ui.detailsscreen.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ecommerceconcept.domain.model.details.DetailsItemDomain

class DetailsPagerAdapter(fa: FragmentActivity, val details: DetailsItemDomain) :
    FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return Details(details)
    }
}

