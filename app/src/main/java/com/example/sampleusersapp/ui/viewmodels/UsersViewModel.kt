package com.example.sampleusersapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleusersapp.data.local.NavigationAction
import com.example.sampleusersapp.domain.interfaces.INavigationEmitter
import com.example.sampleusersapp.domain.interfaces.IUserRepository
import com.example.sampleusersapp.domain.models.UserModel
import com.example.sampleusersapp.ui.navigation.AppRoutes
import com.example.sampleusersapp.ui.navigation.AppRoutesArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: IUserRepository,
    private val navigationEmitter: INavigationEmitter,
) : ViewModel() {

    private val _users = MutableStateFlow<List<UserModel>>(emptyList())
    val users = _users.asStateFlow()


    init {
        viewModelScope.launch {
            val result = repository.getUsers()
            _users.value = result
        }
    }

    fun addUser() {
        viewModelScope.launch {
            val newUser = UserModel(
                id = (_users.value.size + 1).toLong(),
                gender = "Female",
                dateOfBirth = "1903-03-30T00:00:00",
                job = "Android Developer",
                city = "Palo Alto",
                zipcode = "94301",
                latitude = 37.4419,
                profilePicture = "https://randomuser.me/api/portraits/women/2.jpg",
                email = "jane.doe@android.com",
                lastName = "Doe",
                firstName = "Jane",
                phone = "123-456-7891",
                street = "100 Hamilton Avenue",
                state = "CA",
                country = "USA",
                longitude = -122.1430
            )
            repository.addUser(newUser)
            _users.value = repository.getUsers()
        }
    }

    fun onNavigateToUserDetails(userId: Long) {
        viewModelScope.launch {
            navigationEmitter.post(
                NavigationAction.NavigateToWithArgs(
                    route = AppRoutes.USERS_DETAILS,
                    args = mapOf(AppRoutesArgs.USER_ID to userId.toString())
                )
            )
        }
    }

}