package com.example.rewetask.preference

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object ThemePreference {
    private const val PREFERENCE_NAME = "app_theme_prefs"
    private const val KEY_PREFERENCE = "dark_mode"

    private val Context.dataStore by preferencesDataStore(PREFERENCE_NAME)
    private val THEME_KEY = booleanPreferencesKey(KEY_PREFERENCE)

    suspend fun saveThemeSetting(context: Context, isDarkMode: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkMode
        }
    }

    fun getThemeSetting(context: Context): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }
}