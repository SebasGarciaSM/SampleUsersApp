package com.example.sampleusersapp.data.repositories

import com.example.sampleusersapp.data.services.UsersApiService
import com.example.sampleusersapp.data.toDomain
import com.example.sampleusersapp.domain.interfaces.IUserRepository
import com.example.sampleusersapp.domain.models.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: UsersApiService
) : IUserRepository {

    private var cachedUsers: MutableList<UserModel>? = null

    override suspend fun getUsers(): List<UserModel> {
        return cachedUsers?.toList() ?: run {
            val response = api.fetchUsers()
            val usersList = if (response.isSuccessful) {
                response.body()?.users?.map { it.toDomain() } ?: emptyList()
            } else {
                emptyList()
            }
            cachedUsers = usersList.toMutableList()
            cachedUsers!!.toList()
        }
    }

    override suspend fun getUser(id: Long): UserModel? {
        val users = cachedUsers ?: getUsers()
        return users.find { it.id == id }
    }

    override suspend fun addUser(user: UserModel) {
        if (cachedUsers == null) {
            getUsers()
        }
        cachedUsers?.add(user)
    }
}
