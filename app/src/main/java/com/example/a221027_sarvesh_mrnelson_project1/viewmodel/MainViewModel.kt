package com.example.a221027_sarvesh_mrnelson_project1.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.a221027_sarvesh_mrnelson_project1.data.FoodData

class MainViewModel : ViewModel() {

    var foodData = mutableStateOf(FoodData())
        private set

    // Food Donation
    fun updateFood(
        foodName: String,
        quantity: String,
        location: String
    ) {
        foodData.value = foodData.value.copy(
            foodName = foodName,
            quantity = quantity,
            location = location
        )
    }

    // Register/Profile
    fun updateProfile(
        icNumber: String,
        fullName: String,
        userLocation: String,
        phoneNumber: String,
        username: String,
        password: String
    ) {
        foodData.value = foodData.value.copy(
            icNumber = icNumber,
            fullName = fullName,
            userLocation = userLocation,
            phoneNumber = phoneNumber,
            username = username,
            password = password
        )
    }
}