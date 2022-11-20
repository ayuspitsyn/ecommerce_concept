package com.example.ecommerceconcept.ui.cartscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceconcept.EcommerceApp
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.FragmentCartBinding
import com.example.ecommerceconcept.ui.cartscreen.adapter.CartRecyclerAdapter
import com.example.ecommerceconcept.ui.homescreen.adapter.BestSellerRecyclerAdapter
import com.example.ecommerceconcept.ui.vm.EcommerceViewModel
import com.example.ecommerceconcept.ui.vm.EcommerceViewModelFactory

class CartFragment : Fragment() {

    private val viewModel: EcommerceViewModel by viewModels {
        EcommerceViewModelFactory(
            (requireActivity().application as EcommerceApp).database.ecommerceDao(),
            requireActivity().application.assets
        )
    }

    private var _binding: FragmentCartBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        val cartRecyclerView: RecyclerView = binding.cartRecycler
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.cart.observe(this@CartFragment.viewLifecycleOwner) {
            cartRecyclerView.adapter = CartRecyclerAdapter(it.items)
            binding.totalValue.text=it.total.toString()
            binding.deliveryValue.text=it.delivery
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}