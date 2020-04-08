package com.example.mangga.screen.mangadetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MangaDetailViewModel(mangaId : Long) : ViewModel() {

    private val _mangaId = MutableLiveData<Long>()
    val mangaId: LiveData<Long>
        get() = _mangaId

    init {
        _mangaId.value = mangaId
    }
}
