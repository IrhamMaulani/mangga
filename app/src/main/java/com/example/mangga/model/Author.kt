package com.example.mangga.model

import com.squareup.moshi.Json

data class Author(
    @Json(name = "mal_id") var id : Long = 0,
    @Json(name = "type") var type : String = "",
    @Json(name = "name") var authorName : String = ""
)