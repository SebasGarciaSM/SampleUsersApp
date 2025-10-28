package com.example.sampleusersapp.data.models

import com.google.gson.annotations.SerializedName

data class UsersApiResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: Boolean,
    @SerializedName("total_users") val totalUsers: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("users") val users: List<User>,
)
