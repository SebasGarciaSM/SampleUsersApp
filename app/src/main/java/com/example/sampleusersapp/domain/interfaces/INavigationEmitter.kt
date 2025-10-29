package com.example.sampleusersapp.domain.interfaces

import com.example.sampleusersapp.data.local.NavigationAction

interface INavigationEmitter {
    suspend fun post(action: NavigationAction)
}