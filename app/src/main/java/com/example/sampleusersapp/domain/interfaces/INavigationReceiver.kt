package com.example.sampleusersapp.domain.interfaces

import com.example.sampleusersapp.data.local.NavigationAction
import kotlinx.coroutines.flow.Flow

interface INavigationReceiver {
    val navigation: Flow<NavigationAction>
}