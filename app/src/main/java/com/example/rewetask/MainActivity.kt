package com.example.rewetask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rewetask.preference.ThemePreference
import com.example.rewetask.ui.navigation.AppNavigationHost
import com.example.rewetask.ui.theme.REWETaskTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                ThemePreference.getThemeSetting(this@MainActivity).collect { isDarkMode ->
                    setContent {
                        REWETaskTheme(isDarkMode) {
                            AppNavigationHost()
                        }
                    }
                }
            }
        }
    }
}
