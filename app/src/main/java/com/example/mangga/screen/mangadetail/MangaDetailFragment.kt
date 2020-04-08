package com.example.mangga.screen.mangadetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.mangga.MainActivityViewModel
import com.example.mangga.databinding.MangaDetailFragmentBinding


class MangaDetailFragment : Fragment() {

    private var _binding: MangaDetailFragmentBinding? = null

    private lateinit var viewModel: MangaDetailViewModel

    private  lateinit var viewModelFactory: MangaDetailFactory

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MangaDetailFragmentBinding.inflate(inflater, container, false)

        val mangaDetailFragmentArgs by navArgs<MangaDetailFragmentArgs>()

        viewModelFactory = MangaDetailFactory(mangaDetailFragmentArgs.mangaId)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MangaDetailViewModel::class.java)

        activity?.let {
            val mainActivityViewModel: MainActivityViewModel =ViewModelProvider(it)
                .get(MainActivityViewModel::class.java)
            mainActivityViewModel.setToolBarTitle(mangaDetailFragmentArgs.mangaId.toString())
        }

////        binding.tvCheck.text = scoreFragmentArgs.mangaId.toString()
//        binding.root.tv_toolbar_name.text = "adada"

        return binding.root
    }
}

