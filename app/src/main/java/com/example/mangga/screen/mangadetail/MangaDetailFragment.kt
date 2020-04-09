package com.example.mangga.screen.mangadetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mangga.R
import com.example.mangga.databinding.MangaDetailFragmentBinding
import com.example.mangga.model.Genre
import com.example.mangga.model.Manga
import com.example.mangga.util.convertDateStringToYearAndMonth
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.tv_genre.view.*

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

        binding.cvBackground.setBackgroundResource(R.drawable.top_only_radius)

        val mangaDetailFragmentArgs by navArgs<MangaDetailFragmentArgs>()

        viewModelFactory = MangaDetailFactory(mangaDetailFragmentArgs.mangaId)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MangaDetailViewModel::class.java)

        viewModel.mangaDetail.observe(viewLifecycleOwner, Observer {
            setComponent(it)
        })

        return binding.root
    }


    private fun setComponent(mangaDetail : Manga) {
        setImage(mangaDetail.imageUrl)

        binding.tvMangaTitle.text = mangaDetail.title

        val stringBinding:StringBuilder = StringBuilder()
        mangaDetail.authorResults!!.forEach{
            stringBinding.append("${it.authorName}; ")
        }
        binding.tvAuthor.text = getString(R.string.author, stringBinding)

        binding.tvDescription.text = mangaDetail.synopsis

        binding.tvMangaScore.text = mangaDetail.score

        binding.tvMangaDate.text = mangaDetail.publishedResult?.startDate?.let {
            convertDateStringToYearAndMonth(
                it
            )
        }

        setGenreComponent(mangaDetail.genreResults)
    }

    private fun setGenreComponent(genreResults : List<Genre>?) {
        genreResults?.forEach{
            @SuppressLint("InflateParams")
            val view = LayoutInflater.from(context).inflate(R.layout.tv_genre, null)

            view.tv_genre.text = it.genreName

            val params = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT)

            params.setMargins(8,8,8,8)

            view.tv_genre.layoutParams = params
            binding.flexboxGenre.addView(view)
        }
    }

    private fun setImage(imageUrl: String?){
        Glide.with(binding.ivMangaCover)
            .load(imageUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image_gray_24dp))
            .into(binding.ivMangaCover)
    }
}

