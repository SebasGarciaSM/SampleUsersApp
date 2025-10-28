package com.example.sampleusersapp.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Long,
    @SerializedName("gender") val gender: String,
    @SerializedName("date_of_birth") val dateOfBirth: String,
    @SerializedName("job") val job: String,
    @SerializedName("city") val city: String,
    @SerializedName("zipcode") val zipcode: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("profile_picture") val profilePicture: String,
    @SerializedName("email") val email: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("street") val street: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("longitude") val longitude: Double
)
