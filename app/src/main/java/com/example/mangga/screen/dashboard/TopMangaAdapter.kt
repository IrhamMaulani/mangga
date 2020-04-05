package com.example.mangga.screen.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mangga.databinding.TopMangaItemBinding
import com.example.mangga.model.Manga

class TopMangaAdapter (private val listManga : ArrayList<Manga>) : RecyclerView.Adapter<TopMangaAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopMangaAdapter.ListViewHolder {
        val binding = TopMangaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listManga.size

    override fun onBindViewHolder(holder: TopMangaAdapter.ListViewHolder, position: Int) {
        holder.bind(listManga[position])
    }

    inner class ListViewHolder (private val itemBinding: TopMangaItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(manga : Manga){
            itemBinding.tvMangaTitle.text = manga.title
            itemBinding.tvMangaReleaseDate.text = manga.startDate
            itemBinding.tvMangaScore.text = manga.score
        }

    }

}