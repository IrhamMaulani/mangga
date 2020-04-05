package com.example.mangga.model

import com.squareup.moshi.Json

data class Manga (
    @Json(name = "mal_id") var id : String = "",
    @Json(name = "title") var title : String = "",
    @Json(name = "start_date") var startDate : String = "",
    @field:Json(name = "end_date") var endDate : String = "",
    @Json(name = "score") var score : String = "",
    @field:Json(name = "volumes") var volumes : String = "",
    @Json(name = "image_url") var imageUrl : String = ""
)

data class MangaList(
    @Json(name = "results") var results: List<Manga>
)