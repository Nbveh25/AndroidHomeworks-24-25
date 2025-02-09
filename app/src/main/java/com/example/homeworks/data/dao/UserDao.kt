package com.example.homeworks.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homeworks.data.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE name = :name AND password = :password")
    suspend fun getUserByNameAndPass(name: String, password: String): UserEntity?

    @Query("SELECT * FROM users WHERE name = :name")
    suspend fun getUserByName(name: String): UserEntity?
}