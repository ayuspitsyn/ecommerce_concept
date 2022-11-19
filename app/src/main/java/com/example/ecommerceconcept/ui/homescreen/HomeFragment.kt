package com.example.ecommerceconcept.ui.homescreen

import android.os.Bundle
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
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtons()

        setupDropDownSpinners()

        val hotSalesPager: ViewPager2 = binding.hotSalesPager
        homeFragmentViewModel.hotSales.observe(this@HomeFragment.viewLifecycleOwner) {
            hotSalesPager.adapter = HotSalesRecyclerAdapter(it)
            hotSalesPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        val bestSalesRecyclerView: RecyclerView = binding.bestSellerList
        bestSalesRecyclerView.layoutManager = GridLayoutManager(this@HomeFragment.context, 2)
        homeFragmentViewModel.bestSeller.observe(this@HomeFragment.viewLifecycleOwner) {
            bestSalesRecyclerView.adapter = BestSellerRecyclerAdapter(it) {
                findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
            }
        }

    }

    private fun setupButtons() {

        binding.categorySelector.setOnCheckedChangeListener { _, checkedId ->
            binding.phonesTv.setTextColor(requireContext().getColor(R.color.design_blue))
            binding.computersTv.setTextColor(requireContext().getColor(R.color.design_blue))
            binding.healthTv.setTextColor(requireContext().getColor(R.color.design_blue))
            binding.booksTv.setTextColor(requireContext().getColor(R.color.design_blue))
            when (checkedId) {
                binding.phone.id -> binding.phonesTv.setTextColor(requireContext().getColor(R.color.design_orange))
                binding.computer.id -> binding.computersTv.setTextColor(requireContext().getColor(R.color.design_orange))
                binding.health.id -> binding.healthTv.setTextColor(requireContext().getColor(R.color.design_orange))
                binding.books.id -> binding.booksTv.setTextColor(requireContext().getColor(R.color.design_orange))
            }
        }

        val mBottomSheet = binding.bottomLayoutContainer.bottomFilterLayout
        val sheetBehavior = BottomSheetBehavior.from(mBottomSheet)

        binding.filterIcon.setOnClickListener {
            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                binding.bestSellerList.visibility = GONE
            } else {
                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                binding.bestSellerList.visibility = VISIBLE
            }
        }

        binding.bottomLayoutContainer.cancelFilterBtn.setOnClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            binding.bestSellerList.visibility = VISIBLE
        }

        binding.bottomLayoutContainer.applyFilterBtn.setOnClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            binding.bestSellerList.visibility = VISIBLE
        }
    }

    private fun setupDropDownSpinners() {

        val locationArray = arrayOf("Zihuatanejo, Gro", "Auto-detect", "Enter location")
        val brandArray = arrayOf("Samsung", "Apple", "Xiaomi")
        val priceArray = arrayOf("$0 - $300", "$300 - $500", "$500 - $1000")
        val sizeArray = arrayOf("0 to 4.5 inches", "4.5 to 5.5 inches", "5.5 to 8 inches")

        val locationAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            locationArray
        )
        binding.locationDropdown.apply {
            setAdapter(locationAdapter)
            setText(locationArray[0], false)
        }

        val brandAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            brandArray
        )
        binding.bottomLayoutContainer.brandDropDown.apply {
            setAdapter(brandAdapter)
            setText(brandArray[0], false)
        }

        val priceAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            priceArray
        )
        binding.bottomLayoutContainer.priceDropDown.apply {
            setAdapter(priceAdapter)
            setText(priceArray[0], false)
        }

        val sizeAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            sizeArray
        )
        binding.bottomLayoutContainer.sizeDropDown.apply {
            setAdapter(sizeAdapter)
            setText(sizeArray[0], false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}