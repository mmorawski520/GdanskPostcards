package com.zschie.gdanskpostcards.ui.gallery

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.zschie.gdanskpostcards.R
import com.zschie.gdanskpostcards.databinding.FragmentGalleryBinding

data class PhotoInfo(
    @DrawableRes val imageId: Int,
    val info: String? = null
)


open class GalleryFragment(private val startsWith: String) : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    private lateinit var photos: List<PhotoInfo>

    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        photos = drawableFields.filter {
            it.name.startsWith(startsWith)
        }.map { photo ->
            PhotoInfo(
                photo.getInt(null),
                R.string::class.java.fields.find {
                    it.name.startsWith(photo.name, ignoreCase = true)
                }?.let { id -> resources.getString(id.getInt(null)) }
            )
        }

        binding.btnNext.setOnClickListener {
            binding.imageView.startAnimation(slide(400f))
        }

        binding.btnPrev.setOnClickListener {
            binding.imageView.startAnimation(slide(-400f))
        }

        binding.btnInfo.setOnClickListener {
            this.onBackPressed("some text")
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun slide(deltaX: Float): Animation {
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

    fun onBackPressed(desc: String) {
        val builder = AlertDialog.Builder(context)

        builder.setMessage(desc)

        builder.setTitle("Description")

        builder.setCancelable(false)

        builder.setNegativeButton("Close") { dialog, which ->
            dialog.cancel()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    companion object {
        val drawableFields = R.drawable::class.java.fields.filter { it.name.startsWith("blured_") }
    }
}