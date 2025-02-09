package com.example.homeworks.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.homeworks.data.entities.CarEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Insert
    suspend fun insertCar(car: CarEntity)

    @Query("SELECT * FROM cars WHERE userId = :userId")
    fun getCarsByUserId(userId: Long): Flow<List<CarEntity>>

    @Delete
    suspend fun deleteCar(car: CarEntity)
} 