package com.example.a221027_sarvesh_mrnelson_project2.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(
        user: UserEntity
    )

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>

    @Query(
        "SELECT * FROM users WHERE username = :username LIMIT 1"
    )
    suspend fun getUserByUsername(
        username: String
    ): UserEntity?

    @Query(
        "SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1"
    )
    suspend fun loginUser(
        username: String,
        password: String
    ): UserEntity?
}