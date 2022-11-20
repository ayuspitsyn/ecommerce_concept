package com.example.ecommerceconcept.ui.detailsscreen.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.FragmentDetailsPagerItemBinding
import com.example.ecommerceconcept.databinding.ItemHotsalesBinding
import com.example.ecommerceconcept.domain.model.details.DetailsItemDomain
import com.example.ecommerceconcept.domain.model.home.HomeStoreItemDomain
import com.example.ecommerceconcept.ui.detailsscreen.DetailsFragment
import com.example.ecommerceconcept.ui.homescreen.adapter.HotSalesRecyclerAdapter

class DetailsPagerAdapter(fa: FragmentActivity, val details: DetailsItemDomain) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = Details(details)
        fragment.arguments = Bundle().apply {
            when (position) {
                else -> null
            }
        }
        return fragment
    }
}

