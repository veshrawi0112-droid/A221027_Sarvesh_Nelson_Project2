package com.example.a221027_sarvesh_mrnelson_project2.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val icNumber: String,
    val fullName: String,
    val userLocation: String,
    val phoneNumber: String,

    val username: String,
    val password: String
)