package com.example.myroom.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroom.data.model.User
import com.example.myroom.respository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository):ViewModel() {
    val allUsers:LiveData<List<User>> =repository.allUsers
    fun insert(user: User)=viewModelScope.launch{
        repository.insert(user)
    }
    fun delate(user: User)=viewModelScope.launch{
        repository.delete(user)
    }
}