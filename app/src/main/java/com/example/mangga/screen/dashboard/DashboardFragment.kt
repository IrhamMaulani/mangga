package com.example.mangga.screen.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
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


        val topMangaAdapter = TopMangaAdapter()
        showRecyclerTopManga(topMangaAdapter)

        viewModel.topMangas.observe(viewLifecycleOwner, Observer {
            topMangaAdapter.listManga = it.results
        })

        val recentMangaAdapter = RecentMangaAdapter()
        showRecyclerRecentManga(recentMangaAdapter)

        viewModel.recentMangas.observe(viewLifecycleOwner, Observer {
            recentMangaAdapter.listManga = it.results
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            setComponentStatus(it)
        })

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            if(it != null){
                NavHostFragment.findNavController(this)
                    .navigate(DashboardFragmentDirections.actionDashboardToMangaDetail(it))
                viewModel.displayMangaDetailComplete()
            }

        })

        return binding.root
    }

    private fun showRecyclerTopManga(topMangaAdapter: TopMangaAdapter) {
        val topMangaRecyclerView = binding.rvTopManga
        topMangaRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        topMangaRecyclerView.adapter = topMangaAdapter

        topMangaAdapter.setOnItemClickCallback(object : TopMangaAdapter.OnItemClickCallback{
            override fun onItemClicked(manga: Manga) {
                viewModel.displayMangaDetail(manga.id)
            }
        })
    }

    private fun showRecyclerRecentManga(recentMangaAdapter: RecentMangaAdapter){
        val recentMangaRecyclerView  = binding.rvRecentManga
        recentMangaRecyclerView.layoutManager = LinearLayoutManager(context)
        recentMangaRecyclerView.adapter = recentMangaAdapter

        recentMangaAdapter.setOnItemClickCallback(object : RecentMangaAdapter.OnItemClickCallback{
            override fun onItemClicked(manga: Manga) {
                viewModel.displayMangaDetail(manga.id)
            }
        })
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
