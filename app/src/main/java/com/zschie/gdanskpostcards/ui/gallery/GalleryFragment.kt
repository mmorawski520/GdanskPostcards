package com.zschie.gdanskpostcards.ui.gallery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zschie.gdanskpostcards.R
import com.zschie.gdanskpostcards.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        fun animate(deltaX: Float): Animation {
            val animation = TranslateAnimation(
                0.0f, deltaX,
                0.0f, 0.0f
            )

            animation.duration = 50
            animation.repeatCount = 1
            animation.repeatMode = 2
            animation.fillAfter = true

            return animation
        }

        binding.btnNext.setOnClickListener {
            binding.imageView.startAnimation(animate(400f))
        }

        binding.btnPrev.setOnClickListener {
            binding.imageView.startAnimation(animate(-400f))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}