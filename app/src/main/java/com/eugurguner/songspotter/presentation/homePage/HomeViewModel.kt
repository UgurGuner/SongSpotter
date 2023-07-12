package com.eugurguner.songspotter.presentation.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eugurguner.songspotter.domain.model.Song
import com.eugurguner.songspotter.domain.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val songRepository: SongRepository
) : ViewModel() {

    private val _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> = _songs

    private val _dataCount = MutableLiveData<Int>()
    val dataCount: LiveData<Int> = _dataCount

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchSongs(searchTerm: String, limit: Int, offset: Int) {
        songRepository.fetchSongs(
            searchTerm,
            limit,
            offset,
            onSuccess = { songs, count ->
                _songs.value = songs
                _dataCount.value = count
            },
            onError = { errorMessage ->
                _error.value = errorMessage
            }
        )
    }

}