package com.example.sampleusersapp.data.repositories

import com.example.sampleusersapp.data.services.UsersApiService
import com.example.sampleusersapp.data.toDomain
import com.example.sampleusersapp.domain.interfaces.IUserRepository
import com.example.sampleusersapp.domain.models.UserModel

class UserRepositoryImpl(
    private val api: UsersApiService
) : IUserRepository {

    override suspend fun getUsers(): List<UserModel> {
        val users = mutableListOf<UserModel>()
        return try {
            val response = api.fetchUsers()
            if (response.isSuccessful) {
                response.body()?.let {
                    for (user in it.users) {
                        users.add(
                            user.toDomain()
                        )
                    }
                }
            }
            users
        } catch (e: Exception) {
            println(e.toString())
            users
        }
    }

}