package dev.fabled.frameworks.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.fabled.domain.repository.preferences.AppPreferencesRepository
import dev.fabled.frameworks.utils.applicationDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class AppPreferencesRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AppPreferencesRepository {

    private val datastore = context.applicationDataStore

    private object PreferencesKeys {
        val launchStateKey = booleanPreferencesKey(name = "launch_state")
    }

    override suspend fun persistLaunchState() {
        datastore.edit { preferences ->
            preferences[PreferencesKeys.launchStateKey] = true
        }
    }

    override val isFirstLaunch: Flow<Boolean> = datastore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { preferences ->
            preferences[PreferencesKeys.launchStateKey] ?: false
        }
}