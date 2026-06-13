package com.example.a221027_sarvesh_mrnelson_project2.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.a221027_sarvesh_mrnelson_project2.data.FoodDonation
import com.example.a221027_sarvesh_mrnelson_project2.data.LoginData
import com.example.a221027_sarvesh_mrnelson_project2.data.UserProfile
import com.example.a221027_sarvesh_mrnelson_project2.data.api.RetrofitInstance
import com.example.a221027_sarvesh_mrnelson_project2.data.firebase.FirebaseRepository
import com.example.a221027_sarvesh_mrnelson_project2.data.room.UserDatabase
import com.example.a221027_sarvesh_mrnelson_project2.data.room.UserEntity
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    // ==================================================
    // REPOSITORIES & DATABASE
    // ==================================================

    private val firebaseRepository = FirebaseRepository()

    private val userDao =
        UserDatabase
            .getDatabase(application)
            .userDao()

    // ==================================================
    // COMMITTED STATES
    // ==================================================

    var foodDonation = mutableStateOf(
        FoodDonation()
    )
        private set

    var userProfile = mutableStateOf(
        UserProfile()
    )
        private set

    var loginData = mutableStateOf(
        LoginData()
    )
        private set

    // ==================================================
    // RETROFIT API STATE
    // ==================================================

    var apiResults = mutableStateListOf<String>()
        private set

    // ==================================================
    // FIREBASE DONATION LIST
    // ==================================================

    var donationsList = mutableStateListOf<FoodDonation>()
        private set

    // ==================================================
    // DRAFT INPUT STATES
    // ==================================================

    // Login
    var loginUsername = mutableStateOf("")
    var loginPassword = mutableStateOf("")

    // Register
    var regIc = mutableStateOf("")
    var regName = mutableStateOf("")
    var regLocation = mutableStateOf("")
    var regPhone = mutableStateOf("")
    var regUsername = mutableStateOf("")
    var regPassword = mutableStateOf("")

    // Food Form
    var foodNameInput = mutableStateOf("")
    var foodQuantityInput = mutableStateOf("")
    var foodLocationInput = mutableStateOf("")

    // Profile Edit
    var editIc = mutableStateOf("")
    var editName = mutableStateOf("")
    var editLocation = mutableStateOf("")
    var editPhone = mutableStateOf("")
    var editUsername = mutableStateOf("")
    var editPassword = mutableStateOf("")

    // ==================================================
    // FOOD METHODS
    // ==================================================

    fun updateFood(
        foodName: String,
        quantity: String,
        location: String
    ) {

        foodDonation.value = FoodDonation(
            foodName = foodName,
            quantity = quantity,
            location = location
        )
    }

    fun saveDonationToFirebase() {

        firebaseRepository.addDonation(
            foodDonation.value
        )
    }

    // ==================================================
    // LOAD DONATIONS FROM FIREBASE
    // ==================================================

    fun loadDonations() {

        firebaseRepository.getDonations { donations ->

            donationsList.clear()

            donationsList.addAll(
                donations
            )
        }
    }

    // ==================================================
    // RETROFIT API
    // ==================================================

    fun searchFoodApi(
        query: String
    ) {

        viewModelScope.launch {

            try {

                val response =
                    RetrofitInstance.api.searchFood(
                        query
                    )

                apiResults.clear()

                response.meals.forEach { meal ->

                    apiResults.add(
                        meal.strMeal
                    )
                }

            } catch (e: Exception) {

                apiResults.clear()

                apiResults.add(
                    "No food found"
                )
            }
        }
    }

    // ==================================================
    // PROFILE METHODS
    // ==================================================

    fun updateProfile(
        icNumber: String,
        fullName: String,
        userLocation: String,
        phoneNumber: String
    ) {

        userProfile.value = UserProfile(
            icNumber = icNumber,
            fullName = fullName,
            userLocation = userLocation,
            phoneNumber = phoneNumber
        )
    }

    // ==================================================
    // LOGIN METHODS
    // ==================================================

    fun updateLogin(
        username: String,
        password: String
    ) {

        loginData.value = LoginData(
            username = username,
            password = password
        )
    }

    // ==================================================
    // REGISTER USER
    // ==================================================

    fun registerUser(
        icNumber: String,
        fullName: String,
        userLocation: String,
        phoneNumber: String,
        username: String,
        password: String
    ) {

        userProfile.value = UserProfile(
            icNumber = icNumber,
            fullName = fullName,
            userLocation = userLocation,
            phoneNumber = phoneNumber
        )

        loginData.value = LoginData(
            username = username,
            password = password
        )

        saveUserToRoom()
    }

    // ==================================================
    // ROOM DATABASE
    // ==================================================

    fun saveUserToRoom() {

        viewModelScope.launch {

            val user = UserEntity(
                icNumber = userProfile.value.icNumber,
                fullName = userProfile.value.fullName,
                userLocation = userProfile.value.userLocation,
                phoneNumber = userProfile.value.phoneNumber,
                username = loginData.value.username,
                password = loginData.value.password
            )

            userDao.insertUser(user)
        }
    }

    fun loginUser(
        username: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {

        viewModelScope.launch {

            val user =
                userDao.loginUser(
                    username,
                    password
                )

            onResult(
                user != null
            )
        }
    }

    // ==================================================
    // CLEAR INPUTS
    // ==================================================

    fun clearFoodInputs() {

        foodNameInput.value = ""
        foodQuantityInput.value = ""
        foodLocationInput.value = ""
    }

    fun clearLoginInputs() {

        loginUsername.value = ""
        loginPassword.value = ""
    }

    fun clearRegisterInputs() {

        regIc.value = ""
        regName.value = ""
        regLocation.value = ""
        regPhone.value = ""
        regUsername.value = ""
        regPassword.value = ""
    }
}