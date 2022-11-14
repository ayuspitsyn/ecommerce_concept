package com.example.ecommerceconcept.ui.homescreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceconcept.EcommerceApp
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.FragmentHomeBinding
import com.example.ecommerceconcept.ui.homescreen.adapter.BestSellerRecyclerAdapter
import com.example.ecommerceconcept.ui.homescreen.adapter.HotSalesRecyclerAdapter
import com.example.ecommerceconcept.ui.homescreen.vm.HomeFragmentViewModel
import com.example.ecommerceconcept.ui.homescreen.vm.HomeFragmentViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior


class HomeFragment : Fragment() {

    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels {
        HomeFragmentViewModelFactory(
            (requireActivity().application as EcommerceApp).database.ecommerceDao(),
            requireActivity().application.assets
        )
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hotSalesPager: ViewPager2 = binding.hotSalesPager

        homeFragmentViewModel.hotSales.observe(this@HomeFragment.viewLifecycleOwner) {
            Log.d("LOG_TAG", "Fragment observe")
            Log.d("LOG_TAG", it.toString())
            hotSalesPager.adapter = HotSalesRecyclerAdapter(it)
            hotSalesPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        }

        val bestSalesRecyclerView: RecyclerView = binding.bestSellerList
        bestSalesRecyclerView.layoutManager = GridLayoutManager(this@HomeFragment.context, 2)


        homeFragmentViewModel.bestSeller.observe(this@HomeFragment.viewLifecycleOwner) {
            bestSalesRecyclerView.adapter = BestSellerRecyclerAdapter(it) {
                findNavController().navigate(com.example.ecommerceconcept.R.id.action_homeFragment_to_detailsFragment)
            }
        }

        val mBottomSheet = binding.bottomLayoutContainer.bottomFilterLayout
        val sheetBehavior = BottomSheetBehavior.from(mBottomSheet)

        binding.filterIcon.setOnClickListener {
            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
                binding.bestSellerList.visibility = GONE
            } else {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
                binding.bestSellerList.visibility = VISIBLE
            }
        }

        binding.bottomLayoutContainer.cancelFilterBtn.setOnClickListener {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            binding.bestSellerList.visibility = VISIBLE
        }

        binding.bottomLayoutContainer.applyFilterBtn.setOnClickListener {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            binding.bestSellerList.visibility = VISIBLE
        }

        val brandArray = arrayOf("Samsung", "Apple", "Xiaomi")
        val priceArray = arrayOf("$0 - $300", "$300 - $500", "$500 - $1000")
        val sizeArray = arrayOf("0 to 4.5 inches", "4.5 to 5.5 inches", "5.5 to 8 inches")

        val brandAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            brandArray
        )
        binding.bottomLayoutContainer.brandDropDown.setAdapter(brandAdapter)

        val priceAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            priceArray
        )
        binding.bottomLayoutContainer.priceDropDown.setAdapter(priceAdapter)

        val sizeAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            sizeArray
        )
        binding.bottomLayoutContainer.sizeDropDown.setAdapter(sizeAdapter)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}