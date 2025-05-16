package com.example.app_traductor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.app_traductor.ui.navegacion.NavigationApp
import com.example.app_traductor.ui.theme.App_traductorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_traductorTheme {
                NavigationApp()
            }
        }
    }
}
