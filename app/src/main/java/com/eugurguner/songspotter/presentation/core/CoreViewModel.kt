package com.eugurguner.songspotter.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugurguner.songspotter.domain.model.CountEntity
import com.eugurguner.songspotter.domain.model.Song
import com.eugurguner.songspotter.domain.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val songRepository: SongRepository
) : ViewModel() {

    fun fetchLocalData() {
        songRepository.fetchLocalData(
            onSuccess = { songs, count ->
                viewModelScope.launch {
                    songRepository.deleteSongs()
                    songRepository.insertSongs(songs)
                    songRepository.insertCountOfSongs(CountEntity(count = count))
                }
            }
        )
    }

    fun getLocalData(onLocalData: (ArrayList<Song>, Int) -> Unit) {

        var dataList: ArrayList<Song>
        var count: Int

        viewModelScope.launch {
            songRepository.getSongs().collectLatest {
                dataList = ArrayList(it)
                count = songRepository.getCountEntity()?.count ?: 0
                onLocalData(dataList, count)
            }
        }
    }

    fun deleteSong(song: Song) {
        viewModelScope.launch {
         songRepository.deleteSong(song)
        }
    }

}