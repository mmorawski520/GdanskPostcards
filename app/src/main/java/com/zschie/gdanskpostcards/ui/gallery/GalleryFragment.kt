package com.zschie.gdanskpostcards.ui.gallery

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
    private var index = 0

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

        binding.imageView.setImageResource(photos[index].imageId)

        binding.btnNext.setOnClickListener {
            if (index == photos.size - 1) {
                this.index = 0
            }
            this.index++

            binding.imageView.startAnimation(slide(400f))
            binding.imageView.setImageResource(photos[index].imageId)

        }

        binding.btnPrev.setOnClickListener {
            if (index == 0) {
                this.index = photos.size - 1
            }
            this.index--

            binding.imageView.startAnimation(slide(-400f))
            binding.imageView.setImageResource(photos[index].imageId)

        }

        binding.btnInfo.setOnClickListener {
            this.onBackPressed("some text")
        }

        binding.returnBtn.setOnClickListener {
            findNavController().popBackStack()
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

        builder.setNegativeButton("Close") { dialog, _ ->
            dialog.cancel()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    companion object {
        val drawableFields = R.drawable::class.java.fields.filter { it.name.startsWith("blured_") }
    }
}