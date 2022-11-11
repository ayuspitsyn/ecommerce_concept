package com.example.ecommerceconcept.ui.homescreen.hotsales

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HotsalesAdapter(fragment: Fragment) : FragmentStateAdapter (fragment) {
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun createFragment(position: Int): Fragment {

        val fragment = Hotsales()
        fragment.arguments = Bundle().apply {

        }
        return fragment
    }
}