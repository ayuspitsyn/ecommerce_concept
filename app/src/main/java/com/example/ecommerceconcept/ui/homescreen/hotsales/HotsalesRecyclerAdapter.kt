package com.example.ecommerceconcept.ui.homescreen.hotsales

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.ItemHotsalesBinding
import com.example.ecommerceconcept.domain.model.home.HomeStoreItemDomain

class HotsalesRecyclerAdapter(val items: List<HomeStoreItemDomain>) :
    RecyclerView.Adapter<HotsalesRecyclerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ItemHotsalesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: HomeStoreItemDomain) {

            binding.iconNewText.visibility = if (item.is_new) View.VISIBLE else View.GONE
            binding.iconNewBackground.visibility = binding.iconNewText.visibility

            binding.title.text = item.title
            binding.subtitle.text = item.subtitle

            binding.buyNowButton.visibility = if (item.is_buy) View.VISIBLE else View.GONE

            Glide.with(binding.root.context)
                .load(item.picture)
                .error(R.drawable.border)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.picture)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val binding = ItemHotsalesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.setData(items[position])
    }
}