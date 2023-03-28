package com.bldevelopers.ankur.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: TodoApi by lazy {
        Retrofit.Builder().baseUrl("https://anispy211.github.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoApi::class.java)
    }
}