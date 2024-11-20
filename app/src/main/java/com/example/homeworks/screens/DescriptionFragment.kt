package com.example.homeworks.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentDescriptionBinding

class DescriptionFragment() : Fragment(R.layout.fragment_description) {
    private lateinit var viewBinding: FragmentDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = arguments?.getString(TITLE)
        val singer = arguments?.getString(SINGER)
        val imageUrl = arguments?.getString(IMAGE_URL)
        val desc = arguments?.getString(DESC)

        with(viewBinding) {
            titleTv.text = title
            singerTv.text = singer
            descTv.text = desc

            Glide.with(this@DescriptionFragment).load(imageUrl).into(albumIv)
        }
    }

    companion object {
        private const val TITLE = "TITLE"
        private const val SINGER = "SINGER"
        private const val IMAGE_URL = "IMAGE_URL"
        private const val DESC = "DESC"

        fun getInstance(
            title: String,
            singer: String,
            imageUrl: String,
            desc: String
        ) : DescriptionFragment {
            val bundle = Bundle()
            bundle.putString(TITLE, title)
            bundle.putString(SINGER, singer)
            bundle.putString(IMAGE_URL, imageUrl)
            bundle.putString(DESC, desc)
            return DescriptionFragment().apply {
                arguments = bundle
            }
        }
    }
}