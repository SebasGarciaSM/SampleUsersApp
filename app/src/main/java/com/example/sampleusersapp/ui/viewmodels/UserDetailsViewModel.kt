package com.example.sampleusersapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleusersapp.domain.interfaces.IUserRepository
import com.example.sampleusersapp.domain.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val repository: IUserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<UserModel?>(null)
    val user = _user.asStateFlow()

    fun getUserData(id: Long) {
        viewModelScope.launch {
            val result = repository.getUser(id)
            _user.value = result
        }
    }

}