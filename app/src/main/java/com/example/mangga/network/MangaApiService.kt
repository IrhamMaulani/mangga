package com.example.mangga.network

import com.example.mangga.model.Manga
import com.example.mangga.model.MangaList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.jikan.moe/v3/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface MangaApiService {
    @GET("search/manga")
    fun getManga(@Query("order_by") orderBy : String, @Query("limit") limit: Int):
            Deferred<MangaList>

    @GET("manga/{id}")
    fun getMangaDetail(@Path("id") id : Long):
            Deferred<Manga>
}

object MangaApi {
    val retrofitService : MangaApiService by lazy { retrofit.create(MangaApiService::class.java) }
}