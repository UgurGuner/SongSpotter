package com.eugurguner.songspotter.presentation.libraryPage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.eugurguner.songspotter.R
import com.eugurguner.songspotter.databinding.FragmentLibraryBinding
import com.eugurguner.songspotter.presentation.core.CoreViewModel
import com.eugurguner.songspotter.presentation.detail.ActivityArtistDetail
import com.eugurguner.songspotter.presentation.homePage.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LibraryFragment : Fragment() {

    private lateinit var binding: FragmentLibraryBinding
    private val coreViewModel: CoreViewModel by viewModels()
    private var adapter: HomeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLibraryBinding.inflate(LayoutInflater.from(context), container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(onBackPressedCallback)
        updateUI()
    }

    private fun updateUI() {

        setUserImage()

        setUpAdapter()

    }

    private fun setUpAdapter() {

        adapter = HomeAdapter(arrayListOf()) {
            Intent(context, ActivityArtistDetail::class.java).apply {
                putExtra("data", it)
                startActivity(this)
            }
        }

        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        coreViewModel.getLocalData { data, count ->
            adapter?.addItems(data)
            setCount(count)
        }

    }

    private fun setCount(count: Int) {
        binding.txtCount.text = getString(R.string.totalCount).replace("{0}", count.toString())
    }

    private fun setUserImage() {

        try {
            Glide.with(binding.root.context).load(R.drawable.profile_image)
                .transform(CenterCrop(), RoundedCorners(160)).into(binding.imgUser)
        } catch (_: Throwable) {
        }

    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity?.finishAffinity()
        }
    }

}