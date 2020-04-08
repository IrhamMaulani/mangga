package com.example.mangga.screen.mangadetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.mangga.MainActivityViewModel
import com.example.mangga.R
import com.example.mangga.databinding.MangaDetailFragmentBinding
import com.google.android.material.appbar.AppBarLayout


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

        val activity = (activity as AppCompatActivity)

        activity.setSupportActionBar(binding.toolbar.toolbar)

        activity.supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

//        binding.cvBackground.setBackgroundResource(R.drawable.top_only_radius)

//        val appBarLayout: AppBarLayout = binding.appBar
//        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener(){
//            var isShow:Boolean = false
//            var scrollChange:Int = -1
//
//        })



        val mangaDetailFragmentArgs by navArgs<MangaDetailFragmentArgs>()

        viewModelFactory = MangaDetailFactory(mangaDetailFragmentArgs.mangaId)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MangaDetailViewModel::class.java)

////        binding.tvCheck.text = scoreFragmentArgs.mangaId.toString()
//        binding.root.tv_toolbar_name.text = "adada"

        return binding.root
    }
}

