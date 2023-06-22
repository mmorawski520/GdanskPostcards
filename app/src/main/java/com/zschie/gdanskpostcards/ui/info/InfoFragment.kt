package com.zschie.gdanskpostcards.ui.info

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zschie.gdanskpostcards.R
import com.zschie.gdanskpostcards.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val names = resources.getStringArray(R.array.contributors_names)
        names.forEach { name ->
            val textView = TextView(requireContext())
            textView.text = name
            if(name == "DEVELOPERS" || name == "GRAPHIC DESIGNERS" || name == "MANAGERS") {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                textView.setTextColor(Color.parseColor("#FFFFFF"))
                textView.setTypeface(null, Typeface.BOLD)
                textView.setPadding(0, 8, 0, 10)
            }else{
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
            }
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            binding.contributorsList.addView(textView)
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
}