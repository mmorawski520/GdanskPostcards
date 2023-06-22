package com.zschie.gdanskpostcards.ui.gallery_selector

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        }

        binding.btnGdynia.setOnClickListener{

        }

        binding.btnSopot.setOnClickListener{

        }

        binding.btnOthers.setOnClickListener{

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}