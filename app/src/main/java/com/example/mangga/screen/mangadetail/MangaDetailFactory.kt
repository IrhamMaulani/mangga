package com.example.mangga.screen.mangadetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MangaDetailFactory (private val mangaId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MangaDetailViewModel::class.java)) {
            return MangaDetailViewModel(mangaId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}