package com.example.mangga.screen.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mangga.R
import com.example.mangga.databinding.RecentMangaItemBinding
import com.example.mangga.model.Manga
import com.example.mangga.util.convertDateStringToYearAndMonth

class RecentMangaAdapter () : RecyclerView.Adapter<RecentMangaAdapter.ListViewHolder>() {
    var listManga =   listOf<Manga>()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    private var onItemClickCallback: OnItemClickCallback? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentMangaAdapter.ListViewHolder {
        val binding = RecentMangaItemBinding.inflate(LayoutInflater.
        from(parent.context), parent, false)
        return  ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listManga.size

    override fun onBindViewHolder(holder: RecentMangaAdapter.ListViewHolder, position: Int) {
        holder.bind(listManga[position])
    }

    inner class ListViewHolder (private val itemBinding: RecentMangaItemBinding):
        RecyclerView.ViewHolder(itemBinding.root){
        fun bind(manga : Manga){
            itemBinding.tvMangaTitle.text = manga.title
            itemBinding.tvMangaReleaseDate.text = convertDateStringToYearAndMonth(manga.startDate)
            itemBinding.tvMangaScore.text = manga.score
            itemBinding.tvMangaSynopsis.text = manga.synopsis

            Glide.with(itemBinding.ivMangaCover.context)
                .load(manga.imageUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image_gray_24dp))
                .into(itemBinding.ivMangaCover)

            if (manga.stillPublish) {
                itemBinding.tvStatus.text = itemView.context.getString(R.string.on_going)
                itemBinding.ivStatus.setImageResource(R.drawable.on_going_manga)
            }else{
                itemBinding.tvStatus.text = itemView.context.getString(R.string.completed)
                itemBinding.ivStatus.setImageResource(R.drawable.completed_manga)
            }

            itemView.setOnClickListener{ onItemClickCallback?.onItemClicked(manga)}
        }
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(manga: Manga)
    }
}