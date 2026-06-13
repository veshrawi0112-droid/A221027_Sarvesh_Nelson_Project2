package com.example.a221027_sarvesh_mrnelson_project2.data.api

data class Meal(
    val strMeal: String
)

data class FoodResponse(
    val meals: List<Meal>
)