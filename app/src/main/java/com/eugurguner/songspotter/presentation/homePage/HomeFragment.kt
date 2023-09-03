package com.eugurguner.songspotter.presentation.homePage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.eugurguner.songspotter.R
import com.eugurguner.songspotter.databinding.FragmentHomeBinding
import com.eugurguner.songspotter.presentation.detail.ActivityArtistDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentHomeBinding
    private var adapter: HomeAdapter? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private val limit = 20
    private var isLoadedAllData = false
    private var isLoadingPagination = false
    private var currentPage = 0
    private val searchTerm = "jack johnson"
    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(context), container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefresh.setOnRefreshListener(this)
        activity?.onBackPressedDispatcher?.addCallback(onBackPressedCallback)
        updateUI()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateUI() {
        changeLoadingStatus(isLoading = true)

        homeViewModel.songs.observe(viewLifecycleOwner) {
            isLoadingPagination = false

            changeLoadingStatus(isLoading = false)

            adapter?.addItems(it ?: arrayListOf())
        }

        homeViewModel.dataCount.observe(viewLifecycleOwner) {
            if (it == 0) {
                isLoadedAllData = true
            }
        }

        homeViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        setUpAdapter()

        setUserImage()
    }

    private fun setUpAdapter() {
        adapter = HomeAdapter(arrayListOf()) {
            Intent(context, ActivityArtistDetail::class.java).apply {
                putExtra("data", it)
                startActivity(this)
            }
        }

        binding.recyclerView.adapter = adapter

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.recyclerView.layoutManager = linearLayoutManager

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            @SuppressLint("NotifyDataSetChanged")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (adapter?.list.isNullOrEmpty()) return

                if (isLoadedAllData) return

                if (isLoadingPagination) return

                val lastPosition = linearLayoutManager?.findLastVisibleItemPosition()

                if ((lastPosition ?: 0) >= ((adapter?.list?.size ?: 0) - 2)) {
                    currentPage++

                    isLoadingPagination = true

                    fetchList()
                }
            }
        })

        fetchList(isRefreshing = true)
    }

    private fun fetchList(isRefreshing: Boolean = false) {
        changeLoadingStatus(isLoading = isRefreshing)

        if (isRefreshing) {
            isLoadingPagination = false

            isLoadedAllData = false

            currentPage = 0

            adapter?.list = arrayListOf()
        }

        homeViewModel.fetchSongs(
            searchTerm = searchTerm,
            limit = limit,
            offset = currentPage * limit
        )
    }

    private fun setUserImage() {
        try {
            Glide.with(binding.root.context).load(R.drawable.profile_image)
                .transform(CenterCrop(), RoundedCorners(160)).into(binding.imgUser)
        } catch (_: Throwable) {
        }
    }

    override fun onRefresh() {
        binding.swipeRefresh.isRefreshing = false

        isLoadingPagination = false

        isLoadedAllData = false

        currentPage = 0

        fetchList(isRefreshing = true)
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity?.finishAffinity()
        }
    }

    private fun changeLoadingStatus(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }
}