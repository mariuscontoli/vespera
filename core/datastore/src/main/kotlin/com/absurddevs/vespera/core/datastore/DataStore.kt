package com.absurddevs.vespera.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

fun <T> Context.getDataStoreValue(key: Preferences.Key<T>, defaultValue: T) : Flow<T> {
    return dataStore.data
        .map { preferences ->
            preferences[key] ?: defaultValue
        }
}

object DataStoreKeys {
    val SETTINGS_DARK_MODE = intPreferencesKey("settings_dark_mode")
    val SETTINGS_DYNAMIC_THEMING = booleanPreferencesKey("settings_dynamic_theming")

    enum class DARK_MODE {
        FOLLOW_SYSTEM,
        YES,
        NO
    }
}

