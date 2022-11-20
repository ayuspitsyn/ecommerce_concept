package com.example.ecommerceconcept.ui.detailsscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceconcept.EcommerceApp
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.FragmentDetailsBinding
import com.example.ecommerceconcept.domain.model.details.DetailsItemDomain
import com.example.ecommerceconcept.domain.model.details.formattedPrice
import com.example.ecommerceconcept.ui.detailsscreen.adapter.DetailsImageAdapter
import com.example.ecommerceconcept.ui.detailsscreen.adapter.DetailsPagerAdapter
import com.example.ecommerceconcept.ui.detailsscreen.adapter.HorizontalMarginItemDecoration
import com.example.ecommerceconcept.ui.homescreen.vm.HomeFragmentViewModel
import com.example.ecommerceconcept.ui.homescreen.vm.HomeFragmentViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.roundToInt

class DetailsFragment() : Fragment() {

    private val viewModel: HomeFragmentViewModel by viewModels {
        HomeFragmentViewModelFactory(
            (requireActivity().application as EcommerceApp).database.ecommerceDao(),
            requireActivity().application.assets
        )
    }

    private var _binding:FragmentDetailsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getItemDetails(0).observe(this@DetailsFragment.viewLifecycleOwner) {

            binding.imagePager.apply {
                adapter=DetailsImageAdapter(it.images.asList())
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                addItemDecoration(
                    HorizontalMarginItemDecoration(
                    context,
                    R.dimen.viewpager_current_item_horizontal_margin)
                )

                val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
                val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
                val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
                val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                    page.translationX = -pageTranslationX * position
                    page.scaleY = 1 - (0.25f * (position))
                }
                setPageTransformer(pageTransformer)

            }

            binding.title.text=it.title

            setRating(it.rating.roundToInt())

            binding.detailsPager.adapter=DetailsPagerAdapter(requireActivity(), it)
            TabLayoutMediator(binding.tabLayout, binding.detailsPager) {tab, position ->
                when (position) {
                    0 -> tab.text = "Shop"
                    1 -> tab.text = "Details"
                    2 -> tab.text = "Features"
                }
            }.attach()

            binding.addToCartBtn.text=requireContext().resources.getString(R.string.addToCartButtonText, it.formattedPrice())

        }
    }

    private fun setRating(rating: Int) {
        when (rating) {
            5 ->{
                binding.star5.setImageResource(R.drawable.ic_star)
                binding.star4.setImageResource(R.drawable.ic_star)
                binding.star3.setImageResource(R.drawable.ic_star)
                binding.star2.setImageResource(R.drawable.ic_star)
                binding.star1.setImageResource(R.drawable.ic_star)
            }
            4 -> {
                binding.star5.setImageResource(R.drawable.ic_star_empty)
                binding.star4.setImageResource(R.drawable.ic_star)
                binding.star3.setImageResource(R.drawable.ic_star)
                binding.star2.setImageResource(R.drawable.ic_star)
                binding.star1.setImageResource(R.drawable.ic_star)
            }
            3 -> {
                binding.star5.setImageResource(R.drawable.ic_star_empty)
                binding.star4.setImageResource(R.drawable.ic_star_empty)
                binding.star3.setImageResource(R.drawable.ic_star)
                binding.star2.setImageResource(R.drawable.ic_star)
                binding.star1.setImageResource(R.drawable.ic_star)
            }
            2 -> {
                binding.star5.setImageResource(R.drawable.ic_star_empty)
                binding.star4.setImageResource(R.drawable.ic_star_empty)
                binding.star3.setImageResource(R.drawable.ic_star_empty)
                binding.star2.setImageResource(R.drawable.ic_star)
                binding.star1.setImageResource(R.drawable.ic_star)
            }
            else -> {
                binding.star5.setImageResource(R.drawable.ic_star_empty)
                binding.star4.setImageResource(R.drawable.ic_star_empty)
                binding.star3.setImageResource(R.drawable.ic_star_empty)
                binding.star2.setImageResource(R.drawable.ic_star_empty)
                binding.star1.setImageResource(R.drawable.ic_star)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{


    }
}

