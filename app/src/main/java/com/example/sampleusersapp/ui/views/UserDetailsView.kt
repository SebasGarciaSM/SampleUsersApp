package com.example.sampleusersapp.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.sampleusersapp.domain.models.UserModel
import com.example.sampleusersapp.ui.viewmodels.UserDetailsViewModel

@Composable
fun UserDetailsView(
    modifier: Modifier = Modifier,
    viewModel: UserDetailsViewModel = hiltViewModel(),
    onUpClick: () -> Unit,
    userId: Long,
) {

    LaunchedEffect(userId) {
        if (userId != -1L) {
            viewModel.getUserData(userId)
        }
    }

    val user by viewModel.user.collectAsStateWithLifecycle()

    user?.let {
        UserDetailsViewContent(
            modifier = modifier,
            onUpClick = onUpClick,
            user = it,
        )
    } ?: run {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserDetailsViewContent(
    modifier: Modifier = Modifier,
    user: UserModel,
    onUpClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${user.firstName} ${user.lastName}") },
                navigationIcon = {
                    IconButton(onClick = onUpClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                AsyncImage(
                    model = user.profilePicture,
                    contentDescription = "User profile picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                ListItem(
                    headlineContent = { Text("Email") },
                    supportingContent = { Text(user.email) })
            }
            item {
                ListItem(
                    headlineContent = { Text("Phone") },
                    supportingContent = { Text(user.phone) })
            }
            item {
                ListItem(
                    headlineContent = { Text("Job") },
                    supportingContent = { Text(user.job) })
            }
            item {
                ListItem(
                    headlineContent = { Text("Address") },
                    supportingContent = { Text("${user.street}, ${user.city}, ${user.state} ${user.zipcode}, ${user.country}") })
            }
            item {
                ListItem(
                    headlineContent = { Text("Date of Birth") },
                    supportingContent = { Text(user.dateOfBirth) })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserDetailsPreview(modifier: Modifier = Modifier) {
    val dummyUser = UserModel(
        id = 1,
        gender = "Female",
        dateOfBirth = "1990-01-01",
        job = "Android Developer",
        city = "Mountain View",
        zipcode = "94043",
        latitude = 37.4220,
        profilePicture = "https://randomuser.me/api/portraits/women/1.jpg",
        email = "jane.doe@example.com",
        lastName = "Doe",
        firstName = "Jane",
        phone = "123-456-7890",
        street = "1600 Amphitheatre Parkway",
        state = "CA",
        country = "USA",
        longitude = -122.0841
    )
    UserDetailsViewContent(user = dummyUser, onUpClick = {})
}