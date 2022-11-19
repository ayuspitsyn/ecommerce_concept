package com.example.ecommerceconcept.ui.homescreen.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.ItemBestsellerBinding
import com.example.ecommerceconcept.domain.model.home.BestSellerItemDomain
import com.example.ecommerceconcept.domain.model.home.formattedDiscountPrice
import com.example.ecommerceconcept.domain.model.home.formattedPriceWithoutDiscount

class BestSellerRecyclerAdapter(
    private val items: List<BestSellerItemDomain>,
    private val onItemClicked: (BestSellerItemDomain) -> Unit
) :
    RecyclerView.Adapter<BestSellerRecyclerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(private val binding: ItemBestsellerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: BestSellerItemDomain) {

            if (item.is_favorites) {
                binding.favorites.setImageResource(R.drawable.ic_favourites_checked)
            } else {
                binding.favorites.setImageResource(R.drawable.ic_favourites)
            }

            binding.title.text = item.title
            binding.discountPrice.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                text = item.formattedDiscountPrice()
            }
            binding.priceWithoutDiscount.text = item.formattedPriceWithoutDiscount()

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
        holder.itemView.setOnClickListener {onItemClicked(items[position])}
    }


}