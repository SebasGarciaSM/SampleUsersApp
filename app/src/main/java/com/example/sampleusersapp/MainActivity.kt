package com.example.sampleusersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sampleusersapp.domain.interfaces.INavigationEmitter
import com.example.sampleusersapp.domain.interfaces.INavigationReceiver
import com.example.sampleusersapp.ui.navigation.AppNavHost
import com.example.sampleusersapp.ui.theme.SampleUsersAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationEmitter: INavigationEmitter

    @Inject
    lateinit var navigationReceiver: INavigationReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SampleUsersAppTheme {
                AppNavHost(navigationReceiver = navigationReceiver)
            }
        }
    }
}