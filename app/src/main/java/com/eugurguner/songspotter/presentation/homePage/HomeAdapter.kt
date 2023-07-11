package com.eugurguner.songspotter.presentation.homePage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugurguner.songspotter.databinding.ArtistItemVerticalBinding
import com.eugurguner.songspotter.domain.model.Song

class HomeAdapter(
    var list: ArrayList<Song>,
    var onSongClicked: (Song) -> Unit
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        val binding =
            ArtistItemVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        holder.bindItems(list[position])

        holder.itemView.setOnClickListener { onSongClicked(list[position]) }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list: List<Song>) {

        this.list.addAll(list)

        notifyDataSetChanged()

    }

}