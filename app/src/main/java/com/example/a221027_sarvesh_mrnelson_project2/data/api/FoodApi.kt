package com.example.a221027_sarvesh_mrnelson_project2.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {

    @GET("search.php")
    suspend fun searchFood(
        @Query("s") query: String
    ): FoodResponse
}