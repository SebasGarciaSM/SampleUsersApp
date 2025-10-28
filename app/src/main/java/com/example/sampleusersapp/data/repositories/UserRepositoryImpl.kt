package com.example.sampleusersapp.data.repositories

import com.example.sampleusersapp.data.services.UsersApiService
import com.example.sampleusersapp.data.toDomain
import com.example.sampleusersapp.domain.interfaces.IUserRepository
import com.example.sampleusersapp.domain.models.UserModel
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UsersApiService
) : IUserRepository {

    private var cachedUsers: List<UserModel>? = null

    override suspend fun getUsers(): List<UserModel> {
        val users = mutableListOf<UserModel>()
        return cachedUsers ?: run {
            val response = api.fetchUsers()
            if (response.isSuccessful) {
                val results = response.body()?.let {
                    it.users.map { it.toDomain() }
                }
                results?.let {
                    users.addAll(it)
                }
            }
            users
        }
    }

    override suspend fun getUser(id: Long): UserModel? {
        val users = cachedUsers ?: getUsers()
        return users.find { it.id == id }
    }


}