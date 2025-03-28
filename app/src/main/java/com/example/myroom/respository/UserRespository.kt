package com.example.myroom.respository

import androidx.lifecycle.LiveData
import com.example.myroom.data.dao.UserDao
import com.example.myroom.data.model.User

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()
    suspend fun insert(user: User) {
        userDao.insert(user)
    }
    suspend fun update(user: User){
        userDao.update(user)
    }
    suspend fun delete(user: User) {
        userDao.delete(user)
    }
}
