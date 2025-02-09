package com.example.homeworks.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homeworks.data.dao.CarDao
import com.example.homeworks.data.dao.UserDao
import com.example.homeworks.data.entities.CarEntity
import com.example.homeworks.data.entities.UserEntity

@Database(
    entities = [UserEntity::class, CarEntity::class],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val carDao: CarDao
}