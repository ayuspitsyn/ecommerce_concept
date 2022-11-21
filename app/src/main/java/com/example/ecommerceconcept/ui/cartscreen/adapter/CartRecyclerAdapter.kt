package com.example.ecommerceconcept.ui.cartscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.ItemCartBinding
import com.example.ecommerceconcept.domain.model.cart.BasketItemDomain
import com.example.ecommerceconcept.domain.model.cart.formattedPrice

class CartRecyclerAdapter(
    private val cart: List<BasketItemDomain>
) :
    RecyclerView.Adapter<CartRecyclerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: BasketItemDomain) {

            var amount = 1
            binding.amount.text = amount.toString()

            binding.incBtn.setOnClickListener {
                amount++
                binding.amount.text = amount.toString()
            }

            binding.decBtn.setOnClickListener {
                if (amount > 1) amount--
                binding.amount.text = amount.toString()
            }

            binding.title.text = item.title
            binding.price.text = item.formattedPrice()

            Glide.with(binding.root.context)
                .load(item.images)
                .error(R.drawable.border)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.icon)
        }
    }

    override fun getItemCount(): Int = cart.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.setData(cart[position])
    }
}