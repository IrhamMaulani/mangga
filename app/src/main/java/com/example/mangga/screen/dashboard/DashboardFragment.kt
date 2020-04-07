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


        viewModel.topMangas.observe(viewLifecycleOwner, Observer {
            showRecyclerTopManga(it.results)
        })

        viewModel.recentMangas.observe(viewLifecycleOwner, Observer {
            showRecyclerRecentManga(it.results)
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            setComponentStatus(it)
        })

        return binding.root
    }

    private fun showRecyclerTopManga(results: List<Manga>) {
        val topMangaRecyclerView = binding.rvTopManga
        topMangaRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        topMangaRecyclerView.isNestedScrollingEnabled = false
        val topMangaAdapter = TopMangaAdapter()
        topMangaRecyclerView.adapter = topMangaAdapter
        topMangaAdapter.listManga = results
    }

    private fun showRecyclerRecentManga(results : List<Manga>){
        val recentMangaRecyclerView  = binding.rvRecentManga
        recentMangaRecyclerView.layoutManager = LinearLayoutManager(context)
        recentMangaRecyclerView.isNestedScrollingEnabled = false
        val recentMangaAdapter = RecentMangaAdapter()
        recentMangaRecyclerView.adapter = recentMangaAdapter
        recentMangaAdapter.listManga = results
    }

    private fun setComponentStatus(apiStatus: ApiStatus) {
        when (apiStatus) {
            ApiStatus.LOADING -> {
                binding.ivStatus.visibility = View.VISIBLE
                binding.ivStatus.setImageResource(R.drawable.loading_animation)
                binding.groupTvInfo.visibility = View.INVISIBLE
            }
            ApiStatus.ERROR -> {
                binding.ivStatus.visibility = View.VISIBLE
                binding.ivStatus.setImageResource(R.drawable.no_connection)
                binding.groupTvInfo.visibility = View.INVISIBLE
            }
            ApiStatus.DONE -> {
                binding.ivStatus.visibility = View.INVISIBLE
                binding.groupTvInfo.visibility = View.VISIBLE
            }
        }
    }


}
