package com.example.mangga.model

import com.squareup.moshi.Json

data class Manga (
    @Json(name = "mal_id") var id : Long? = 0,
    @Json(name = "title") var title : String? = "",
    @Json(name = "start_date") var startDate : String? = "",
    @Json(name = "end_date") var endDate : String? = "",
    @Json(name = "score") var score : String? = "",
    @Json(name = "volumes") var volumes : String? = "",
    @Json(name = "image_url") var imageUrl : String? = "",
    @Json(name = "synopsis") var synopsis : String? = "",
    @Json(name = "publishing") var stillPublish : Boolean? = false,
    @Json(name = "genres") var  genreResults : List<Genre>?,
    @Json(name = "authors") var authorResults: List<Author>?,
    @Json(name = "published") var publishedResult: Published?
)

data class MangaList(
    @Json(name = "results") var results: List<Manga>
)

data class Published(
    @Json(name = "from") var startDate: String? = ""
)

