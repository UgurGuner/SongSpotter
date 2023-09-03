package com.eugurguner.songspotter.presentation.horizontalListPage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugurguner.songspotter.databinding.HorizontalItemBinding
import com.eugurguner.songspotter.domain.model.Song

class HorizontalListAdapter(
    var list: ArrayList<Song>,
    var onSongClicked: (Song) -> Unit,
    var onDeleteClicked: (Song, Int) -> Unit
) : RecyclerView.Adapter<HorizontalListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalListViewHolder {
        val binding =
            HorizontalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HorizontalListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalListViewHolder, position: Int) {
        holder.bindItems(list[position])

        holder.binding.imgCancel.setOnClickListener {
            onDeleteClicked(list[position], position)
        }

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