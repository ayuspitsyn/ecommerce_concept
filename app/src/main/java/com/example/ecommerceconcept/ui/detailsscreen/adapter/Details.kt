package com.example.ecommerceconcept.ui.detailsscreen.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.FragmentDetailsPagerItemBinding
import com.example.ecommerceconcept.domain.model.details.DetailsItemDomain

class Details(val details: DetailsItemDomain) : Fragment() {

    private var _binding: FragmentDetailsPagerItemBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsPagerItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            cpu.text = details.CPU
            camera.text = details.camera
            ram.text = details.ssd
            flash.text = details.sd
            colorVariant1btn.backgroundTintList =
                (ColorStateList.valueOf(Color.parseColor(details.color[0])))
            colorVariant1btn.setBackgroundResource(R.drawable.oval)
            colorVariant2btn.backgroundTintList =
                (ColorStateList.valueOf(Color.parseColor(details.color[1])))
            colorVariant2btn.setBackgroundResource(R.drawable.oval)

            flashVariant1Btn.text = details.capacity[0]
            flashVariant2Btn.text = details.capacity[1]
        }

        binding.colorVariant1btn.setOnClickListener {
            binding.colorVariant1btn.setIconResource(R.drawable.ic_selected)
            binding.colorVariant2btn.icon = null
        }

        binding.colorVariant2btn.setOnClickListener {
            binding.colorVariant1btn.icon = null
            binding.colorVariant2btn.setIconResource(R.drawable.ic_selected)

        }

        binding.flashVariant1Btn.apply {
            setOnClickListener {
                this.setBackgroundColor(it.rootView.context.getColor(R.color.design_orange))
                this.setTextColor(it.rootView.context.getColor(R.color.white))

                binding.flashVariant2Btn.setBackgroundColor(it.rootView.context.getColor(R.color.white))
                binding.flashVariant2Btn.setTextColor(it.rootView.context.getColor(R.color.design_gray))
            }
        }

        binding.flashVariant2Btn.apply {
            setOnClickListener {
                this.setBackgroundColor(it.rootView.context.getColor(R.color.design_orange))
                this.setTextColor(it.rootView.context.getColor(R.color.white))

                binding.flashVariant1Btn.setBackgroundColor(it.rootView.context.getColor(R.color.white))
                binding.flashVariant1Btn.setTextColor(it.rootView.context.getColor(R.color.design_gray))
            }
        }
    }
}