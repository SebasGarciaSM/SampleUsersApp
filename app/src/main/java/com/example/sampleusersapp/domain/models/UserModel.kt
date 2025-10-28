package com.example.sampleusersapp.domain.models

data class UserModel(
    val id: Long,
    val gender: String,
    val dateOfBirth: String,
    val job: String,
    val city: String,
    val zipcode: String,
    val latitude: Double,
    val profilePicture: String,
    val email: String,
    val lastName: String,
    val firstName: String,
    val phone: String,
    val street: String,
    val state: String,
    val country: String,
    val longitude: Double
)
