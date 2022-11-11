package com.example.ecommerceconcept.ui.homescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.FragmentHomeBinding
import com.example.ecommerceconcept.ui.homescreen.hotsales.Hotsales
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter
import com.example.ecommerceconcept.ui.homescreen.vm.HomeFragmentViewModel
import com.example.ecommerceconcept.ui.homescreen.vm.HomeFragmentViewModelFactory

class HomeFragment : Fragment() {

    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels {
        HomeFragmentViewModelFactory()
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


        binding.hotSalesPager.adapter = HotsalesAdapter(this)

    }
}