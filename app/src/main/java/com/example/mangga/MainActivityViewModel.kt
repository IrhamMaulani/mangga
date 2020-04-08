package com.example.mangga

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _bottomNavigationVisibility = MutableLiveData<Int>()
    val bottomNavigationVisibility: LiveData<Int>
        get() = _bottomNavigationVisibility

    private val _toolBarComponentVisibility = MutableLiveData<Int>()
    val toolBarComponentVisibility: LiveData<Int>
        get() = _toolBarComponentVisibility

    private val _toolBarTitle = MutableLiveData<String>()
    val toolBarTitle: LiveData<String>
        get() = _toolBarTitle

    init {
        showBottomNav()
        showToolBarComponent()
    }

    fun showBottomNav() {
        _bottomNavigationVisibility.postValue(View.VISIBLE)
    }


    fun showToolBarComponent(){
        _toolBarComponentVisibility.postValue(View.VISIBLE)
    }

    fun hideToolBarComponent(){
        _toolBarComponentVisibility.postValue(View.GONE)
    }

    fun hideBottomNav() {
        _bottomNavigationVisibility.postValue(View.GONE)
    }

    fun setToolBarTitle(title : String) {
        _toolBarTitle.value = title
    }

    fun toolBarTitleComplete(appName: String) {
        _toolBarTitle.value = appName.toString()
    }
}