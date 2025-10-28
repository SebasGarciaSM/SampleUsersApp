package com.example.sampleusersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sampleusersapp.data.repositories.UserRepositoryImpl
import com.example.sampleusersapp.data.services.UsersApiService
import com.example.sampleusersapp.ui.theme.SampleUsersAppTheme
import com.example.sampleusersapp.ui.viewmodels.UsersViewModel
import com.example.sampleusersapp.ui.views.UsersView
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SampleUsersAppTheme {
                UsersView()
            }
        }
    }
}