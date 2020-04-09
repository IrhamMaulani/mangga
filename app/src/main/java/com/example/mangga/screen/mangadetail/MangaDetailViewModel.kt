package com.example.mangga.screen.mangadetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangga.model.ApiStatus
import com.example.mangga.model.Manga
import com.example.mangga.model.MangaList
import com.example.mangga.network.MangaApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MangaDetailViewModel(mangaId : Long) : ViewModel() {
    private val _mangaDetail = MutableLiveData<Manga>()
    val mangaDetail: LiveData<Manga>
        get() = _mangaDetail

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private fun getMangaDetail(mangaId : Long) {
        coroutineScope.launch {
            val getMangaDetailDeferred = MangaApi.retrofitService
                .getMangaDetail(mangaId)
            try {
                _status.value = ApiStatus.LOADING
                val mangaResult= getMangaDetailDeferred.await()
                _status.value = ApiStatus.DONE
                _mangaDetail.value = mangaResult
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _mangaDetail.value = null
                Log.d("MangaDetailActivity", "Error $e")
            }
        }
    }
    init {
        getMangaDetail(mangaId)
    }
}
