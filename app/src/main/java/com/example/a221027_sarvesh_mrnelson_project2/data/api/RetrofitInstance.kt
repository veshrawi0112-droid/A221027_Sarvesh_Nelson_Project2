package com.example.a221027_sarvesh_mrnelson_project2.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: FoodApi by lazy {

        Retrofit.Builder()
            .baseUrl(
                "https://www.themealdb.com/api/json/v1/1/"
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(FoodApi::class.java)
    }
}