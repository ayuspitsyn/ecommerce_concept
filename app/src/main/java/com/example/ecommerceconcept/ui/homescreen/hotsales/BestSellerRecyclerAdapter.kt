package com.example.ecommerceconcept.ui.homescreen.hotsales

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.ItemBestsellerBinding
import com.example.ecommerceconcept.domain.model.home.BestSellerItemDomain

class BestSellerRecyclerAdapter(val items: List<BestSellerItemDomain>) :
    RecyclerView.Adapter<BestSellerRecyclerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ItemBestsellerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: BestSellerItemDomain) {

            if (item.is_favorites) {
                binding.favorites.setImageResource(R.drawable.ic_favourites_checked)
            } else {
                binding.favorites.setImageResource(R.drawable.ic_favourites)
            }

            binding.title.text = item.title
            binding.discountPrice.text = item.discount_price.toString()
            binding.priceWithoutDiscount.text = binding.root.context.resources.getString(
                R.string.strikeText,
                item.price_without_discount.toString()
            )

            Glide.with(binding.root.context)
                .load(item.picture)
                .error(R.drawable.border)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.picture)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val binding = ItemBestsellerBinding.inflate(
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