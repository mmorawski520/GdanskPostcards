package com.zschie.gdanskpostcards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.navigation.fragment.findNavController
import com.zschie.gdanskpostcards.databinding.FragmentGallerySelectorBinding

class GallerySelectorFragment : Fragment() {


    private var _binding: FragmentGallerySelectorBinding? = null

    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGallerySelectorBinding.inflate(inflater, container, false)

        binding.btnGdansk.setOnClickListener{
            findNavController().navigate(R.id.gdansk_gallery)
        }

        binding.btnGdynia.setOnClickListener{
            findNavController().navigate(R.id.gdynia_gallery)
        }

        binding.btnSopot.setOnClickListener{
            findNavController().navigate(R.id.sopot_gallery)
        }

        binding.btnOthers.setOnClickListener{
            findNavController().navigate(R.id.other_gallery)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}