package com.example.sampleusersapp.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.sampleusersapp.domain.models.UserModel
import com.example.sampleusersapp.ui.core.composables.UserListItem
import com.example.sampleusersapp.ui.viewmodels.UsersViewModel

@Composable
fun UsersView(
    viewModel: UsersViewModel = hiltViewModel()
) {

    val users = viewModel.users.collectAsState()

    UsersViewContent(
        users = users.value,
        onAddUser = { viewModel.addUser() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UsersViewContent(
    modifier: Modifier = Modifier,
    users: List<UserModel>,
    onAddUser: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Sample Users") },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddUser,
                icon = { Icon(Icons.Default.AddCircle, contentDescription = null) },
                text = { Text("Add User") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            items(users) { user ->
                UserListItem(
                    modifier = Modifier.clickable {},
                    user = user
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersViewPreview(modifier: Modifier = Modifier) {
    UsersViewContent(
        users = emptyList(),
        onAddUser = { }
    )
}