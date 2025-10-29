package com.example.sampleusersapp.domain.interfaces

import com.example.sampleusersapp.domain.models.UserModel
import kotlinx.coroutines.flow.MutableSharedFlow

interface IUserRepository {

    suspend fun getUsers(): List<UserModel>

    suspend fun getUser(id: Long): UserModel?
    suspend fun addUser(user: UserModel)

}