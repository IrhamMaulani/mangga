package com.example.mangga.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat



    @SuppressLint("SimpleDateFormat")
    fun convertDateStringToYearAndMonth(date : String) : String {
        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("MMM yyyy")
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        return formatter.format(parser.parse(date))
    }