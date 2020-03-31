package com.example.mangga.screen.mangalist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mangga.R

class MangaListFragment : Fragment() {

    companion object {
        fun newInstance() = MangaListFragment()
    }

    private lateinit var viewModel: MangaListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.manga_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MangaListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
