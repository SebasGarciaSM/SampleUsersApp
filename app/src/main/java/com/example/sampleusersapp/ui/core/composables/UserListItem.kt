package com.example.sampleusersapp.ui.core.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.sampleusersapp.domain.models.UserModel

@Composable
fun UserListItem(
    modifier: Modifier = Modifier,
    user: UserModel
) {
    UserListItemContent(
        modifier = modifier,
        user = user
    )
}

@Composable
private fun UserListItemContent(
    modifier: Modifier = Modifier,
    user: UserModel,
) {
    ListItem(
        modifier = modifier,
        headlineContent = { Text("${user.firstName} ${user.lastName}") },
        leadingContent = {
            AsyncImage(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                model = user.profilePicture,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = rememberVectorPainter(Icons.Default.AccountCircle),
                error = rememberVectorPainter(Icons.Default.AccountCircle)
            )
        },
        supportingContent = { Text(user.email) },
        trailingContent = {
            Icon(
                Icons.AutoMirrored.Filled.ArrowRight,
                contentDescription = null,
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun UserListItemPreview(modifier: Modifier = Modifier) {
    UserListItemContent(
        user = UserModel(
            id = 1,
            gender = "Male",
            dateOfBirth = "1990-01-01",
            job = "Android Developer",
            city = "Mountain View",
            zipcode = "94043",
            latitude = 37.4220,
            profilePicture = "",
            email = "john.doe@android.com",
            lastName = "Doe",
            firstName = "John",
            phone = "123-456-7890",
            street = "1600 Amphitheatre Parkway",
            state = "CA",
            country = "USA",
            longitude = -122.0841
        )
    )
}