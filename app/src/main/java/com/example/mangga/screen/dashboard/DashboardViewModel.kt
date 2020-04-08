package com.example.mangga.screen.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangga.model.ApiStatus
import com.example.mangga.model.MangaList
import com.example.mangga.network.MangaApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class DashboardViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _recentMangas = MutableLiveData<MangaList>()
    val recentMangas: LiveData<MangaList>
        get() = _recentMangas

    private val _topMangas = MutableLiveData<MangaList>()
    val topMangas: LiveData<MangaList>
        get() = _topMangas

    private val _navigateToDetail = MutableLiveData<Long>()
    val navigateToDetail : LiveData<Long>
        get() = _navigateToDetail

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getMangas()
    }

    private fun getMangas() {
        coroutineScope.launch {
            val getRecentMangaDeferred = MangaApi.retrofitService.getManga("start_date", 20)
            val getTopMangaDeferred = MangaApi.retrofitService.getManga("score", 5)
            try {
                _status.value = ApiStatus.LOADING
                val listRecentMangaResult = getRecentMangaDeferred.await()
                val listTopMangaResult = getTopMangaDeferred.await()
                _status.value = ApiStatus.DONE
                _recentMangas.value = listRecentMangaResult
                _topMangas.value = listTopMangaResult
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _recentMangas.value = MangaList(ArrayList())
            }
        }
    }

    fun displayMangaDetail(mangaId : Long){
        _navigateToDetail.value = mangaId
    }

    fun displayMangaDetailComplete(){
        _navigateToDetail.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}
