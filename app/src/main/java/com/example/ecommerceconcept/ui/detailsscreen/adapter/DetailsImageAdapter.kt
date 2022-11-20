package com.example.ecommerceconcept.ui.detailsscreen.adapter

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.ItemDetailsImageBinding
import com.example.ecommerceconcept.databinding.ItemHotsalesBinding

class DetailsImageAdapter(private val items: List<String>) :
    RecyclerView.Adapter<DetailsImageAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(private val binding: ItemDetailsImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: String) {
            Glide.with(binding.root.context)
                .load(item)
                .error(R.drawable.border)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.image)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ItemDetailsImageBinding.inflate(
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

class HorizontalMarginItemDecoration(context: Context, @DimenRes horizontalMarginInDp: Int) :
    RecyclerView.ItemDecoration() {

    private val horizontalMarginInPx: Int =
        context.resources.getDimension(horizontalMarginInDp).toInt()

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.right = horizontalMarginInPx
        outRect.left = horizontalMarginInPx
    }

}