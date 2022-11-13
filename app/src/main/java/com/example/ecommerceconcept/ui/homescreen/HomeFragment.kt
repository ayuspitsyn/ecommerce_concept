package com.example.ecommerceconcept.ui.homescreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}