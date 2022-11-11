package com.example.ecommerceconcept.ui.homescreen.hotsales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecommerceconcept.R

class Hotsales: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.item_hotsales, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



    }

}