package com.example.homeworks.data.repository

import com.example.homeworks.data.dao.CarDao
import com.example.homeworks.data.entities.CarEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CarRepository(
    private val carDao: CarDao
) {
    suspend fun addCar(car: CarEntity) {
        withContext(Dispatchers.IO) {
            carDao.insertCar(car)
        }
    }

    fun getCarsByUserId(userId: Long): Flow<List<CarEntity>> {
        return carDao.getCarsByUserId(userId)
    }

    suspend fun deleteCar(car: CarEntity) {
        withContext(Dispatchers.IO) {
            carDao.deleteCar(car)
        }
    }
} 