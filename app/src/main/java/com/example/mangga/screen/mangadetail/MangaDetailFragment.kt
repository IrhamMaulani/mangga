package com.example.mangga.screen.mangadetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mangga.R

class MangaDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MangaDetailFragment()
    }

    private lateinit var viewModel: MangaDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.manga_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MangaDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
