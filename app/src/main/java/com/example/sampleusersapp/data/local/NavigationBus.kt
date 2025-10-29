package com.example.sampleusersapp.data.local

import com.example.sampleusersapp.domain.interfaces.INavigationEmitter
import com.example.sampleusersapp.domain.interfaces.INavigationReceiver
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class NavigationBus @Inject constructor() : INavigationReceiver, INavigationEmitter {

    private val _navigation = MutableSharedFlow<NavigationAction>()
    override val navigation = _navigation.asSharedFlow()

    override suspend fun post(action: NavigationAction) {
        _navigation.emit(action)
    }

}

sealed class NavigationAction {
    data class NavigateTo(val route: String, val clearBackStack: Boolean = false) :
        NavigationAction()

    data class NavigateToWithArgs(
        val route: String,
        val args: Map<String, String>,
        val clearBackStack: Boolean = false,
    ) : NavigationAction()

    data object NavigateBack : NavigationAction()
}