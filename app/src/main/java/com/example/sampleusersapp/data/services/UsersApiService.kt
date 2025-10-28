package com.example.sampleusersapp.data.services

import com.example.sampleusersapp.data.models.UsersApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface UsersApiService {

    @GET("users")
    suspend fun fetchUsers(): Response<UsersApiResponse>

}