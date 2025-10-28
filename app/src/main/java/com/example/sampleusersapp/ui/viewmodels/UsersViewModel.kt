package com.example.sampleusersapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleusersapp.domain.interfaces.IUserRepository
import com.example.sampleusersapp.domain.models.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersViewModel(
    private val repository: IUserRepository
) : ViewModel() {

    private val _users = MutableStateFlow(emptyList<UserModel>())
    val users: MutableStateFlow<List<UserModel>> get() = _users


    init {
        viewModelScope.launch {
            val result = repository.getUsers()
            _users.emit(result)
        }
    }

    fun addUser() {
        viewModelScope.launch {
            val newUser = UserModel(
                id = (_users.value.size + 1).toLong(),
                gender = "Female",
                dateOfBirth = "1992-02-02",
                job = "Android Developer",
                city = "Palo Alto",
                zipcode = "94301",
                latitude = 37.4419,
                profilePicture = "",
                email = "jane.doe@android.com",
                lastName = "Doe",
                firstName = "Jane",
                phone = "123-456-7891",
                street = "100 Hamilton Avenue",
                state = "CA",
                country = "USA",
                longitude = -122.1430
            )
            _users.emit(_users.value + newUser)
        }
    }

}