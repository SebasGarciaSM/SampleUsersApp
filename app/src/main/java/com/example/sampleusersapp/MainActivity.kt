package com.example.sampleusersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sampleusersapp.data.repositories.UserRepositoryImpl
import com.example.sampleusersapp.data.services.UsersApiService
import com.example.sampleusersapp.ui.theme.SampleUsersAppTheme
import com.example.sampleusersapp.ui.viewmodels.UsersViewModel
import com.example.sampleusersapp.ui.views.UsersView
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.slingacademy.com/v1/sample-data/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val usersApiService = retrofit.create(UsersApiService::class.java)

        setContent {
            SampleUsersAppTheme {
                UsersView(
                    viewModel = UsersViewModel(
                        repository = UserRepositoryImpl(
                            api = usersApiService
                        )
                    )
                )
            }
        }
    }
}