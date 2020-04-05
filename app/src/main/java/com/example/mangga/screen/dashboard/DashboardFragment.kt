package com.example.mangga.screen.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mangga.R
import com.example.mangga.databinding.DashboardFragmentBinding
import com.example.mangga.model.ApiStatus
import com.example.mangga.model.Manga


class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DashboardViewModel::class.java)
    }
    private var _binding: DashboardFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DashboardFragmentBinding.inflate(inflater, container, false)

        val topMangaRecyclerView = binding.rvRecentManga
        val statusImage = binding.ivStatus


//        viewModel.recentMangas.observe(viewLifecycleOwner, Observer {recentMangas->
//            Log.i("Main Activity", "Recent Manga ${recentMangas.results}")
//        })

        topMangaRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val topMangaAdapter : TopMangaAdapter = TopMangaAdapter()
        topMangaRecyclerView.adapter = topMangaAdapter

        viewModel.topMangas.observe(viewLifecycleOwner, Observer {
            topMangaAdapter.listManga = it.results
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            when (it) {
                ApiStatus.LOADING -> {
                    Log.i("Main Activity", it.toString())
                    statusImage.visibility = View.VISIBLE
                    statusImage.setImageResource(R.drawable.loading_animation)
                }
                ApiStatus.ERROR -> {
                    Log.i("Main Activity", it.toString())
                    statusImage.visibility = View.VISIBLE
//                    statusImage.setImageResource(R.drawable.no_connection)
                    statusImage.setImageResource(R.drawable.loading_animation)
                }
                ApiStatus.DONE -> {
                    Log.i("Main Activity", it.toString())
                    statusImage.visibility = View.INVISIBLE
                }
            }
        })

        return binding.root
    }



}
