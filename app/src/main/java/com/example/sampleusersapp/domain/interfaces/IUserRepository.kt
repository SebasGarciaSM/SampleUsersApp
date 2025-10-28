package com.example.sampleusersapp.domain.interfaces

import com.example.sampleusersapp.domain.models.UserModel

interface IUserRepository {

    suspend fun getUsers(): List<UserModel>

}