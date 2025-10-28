package com.example.sampleusersapp.data

import com.example.sampleusersapp.data.models.User
import com.example.sampleusersapp.domain.models.UserModel


fun User.toDomain(): UserModel = UserModel(
    id = id,
    gender = gender,
    dateOfBirth = dateOfBirth,
    job = job,
    city = city,
    zipcode = zipcode,
    latitude = latitude,
    profilePicture = profilePicture,
    email = email,
    lastName = lastName,
    firstName = firstName,
    phone = phone,
    street = street,
    state = state,
    country = country,
    longitude = longitude,
)
