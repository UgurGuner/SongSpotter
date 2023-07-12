package com.eugurguner.songspotter.presentation.horizontalListPage

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
import com.eugurguner.songspotter.databinding.FragmentHorizontalListBinding
import com.eugurguner.songspotter.domain.model.Song
import com.eugurguner.songspotter.presentation.core.CoreViewModel
import com.eugurguner.songspotter.presentation.detail.ActivityArtistDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHorizontalList : Fragment() {

    private lateinit var binding: FragmentHorizontalListBinding
    private val coreViewModel: CoreViewModel by viewModels()
    private var adapter: HorizontalListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            FragmentHorizontalListBinding.inflate(LayoutInflater.from(context), container, false)

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

        adapter = HorizontalListAdapter(
            list = arrayListOf(),
            onSongClicked = {
                Intent(context, ActivityArtistDetail::class.java).apply {
                    putExtra("data", it)
                    startActivity(this)
                }
            }, onDeleteClicked = { song, position ->
                deleteSong(song, position)
            })

        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        coreViewModel.getLocalData { data, _ ->
            adapter?.addItems(data)
        }

    }

    private fun deleteSong(song: Song, position: Int) {

        coreViewModel.deleteSong(song)

        adapter?.list?.removeAt(position)

        adapter?.notifyItemRemoved(position)

        adapter?.notifyItemRangeChanged(position, adapter?.list?.size ?: 0)

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