package com.example.ecommerceconcept.ui.homescreen.hotsales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecommerceconcept.databinding.ItemHotsalesBinding
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter.Companion.ID
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter.Companion.ID_DEFAULT
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter.Companion.IS_BUY
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter.Companion.IS_BUY_DEFAULT
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter.Companion.IS_NEW
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter.Companion.IS_NEW_DEFAULT
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter.Companion.PICTURE
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter.Companion.PICTURE_DEFAULT
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter.Companion.SUBTITLE
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter.Companion.SUBTITLE_DEFAULT
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter.Companion.TITLE
import com.example.ecommerceconcept.ui.homescreen.hotsales.HotsalesAdapter.Companion.TITLE_DEFAULT
import java.net.URI

class Hotsales: Fragment() {

    private var _binding: ItemHotsalesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemHotsalesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val id = if (arguments?.containsKey(ID) == true) {
            requireArguments().getInt(ID, ID_DEFAULT)
        } else ID_DEFAULT

        val isNew = if (arguments?.containsKey(IS_NEW) == true) {
            requireArguments().getBoolean(IS_NEW, IS_NEW_DEFAULT)
        } else IS_NEW_DEFAULT

        val title = if (arguments?.containsKey(TITLE) == true) {
            requireArguments().getString(TITLE, TITLE_DEFAULT)
        } else TITLE_DEFAULT

        val subtitle = if (arguments?.containsKey(SUBTITLE) == true) {
            requireArguments().getString(SUBTITLE, SUBTITLE_DEFAULT)
        } else SUBTITLE_DEFAULT

        val picture = if (arguments?.containsKey(PICTURE) == true) {
            requireArguments().getString(PICTURE, PICTURE_DEFAULT)
        } else PICTURE_DEFAULT

        val isBuy = if (arguments?.containsKey(IS_BUY) == true) {
            requireArguments().getBoolean(IS_BUY, IS_BUY_DEFAULT)
        } else IS_BUY_DEFAULT

        binding.iconNewText.visibility = if (isNew) VISIBLE else GONE
        binding.iconNewBackground.visibility = if (isNew) VISIBLE else GONE

        binding.title.text=title
        binding.subtitle.text=subtitle

        binding.buyNowButton.visibility = if (isBuy) VISIBLE else GONE

        //binding.picture.setImageURI(URI.parse(picture)) //todo

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}