package com.example.rewetask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rewetask.ui.navigation.AppNavigationHost
import com.example.rewetask.ui.theme.REWETaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            REWETaskTheme {
                AppNavigationHost()
            }
        }
    }
}
