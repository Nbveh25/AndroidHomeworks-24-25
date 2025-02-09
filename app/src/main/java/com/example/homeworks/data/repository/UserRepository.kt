package com.example.homeworks.data.repository

import com.example.homeworks.data.dao.UserDao
import com.example.homeworks.data.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val userDao: UserDao
) {

    suspend fun register(name: String, email: String, password: String) {
        return withContext(Dispatchers.IO) {

            val existingUser = userDao.getUserByName(name)
            if (existingUser != null) {
                throw IllegalStateException("Пользователь с таким именем уже существует")
            }

            val user = UserEntity(
                name = name,
                email = email,
                password = password
            )
            
            userDao.saveUser(user)
        }
    }

    suspend fun getUserByNameAndPass(name: String, password: String): UserEntity {
        return withContext(Dispatchers.IO) {
            userDao.getUserByNameAndPass(name = name, password = password)
                ?: throw IllegalStateException("Пользователь не найден")
        }
    }

    suspend fun saveUser(user: UserEntity) {
        return withContext(Dispatchers.IO) {
            userDao.saveUser(user = user)
        }
    }

    suspend fun getUserByName(name: String): UserEntity {
        return withContext(Dispatchers.IO) {
            userDao.getUserByName(name)
                ?: throw IllegalStateException("Пользователь не найден")
        }
    }
}