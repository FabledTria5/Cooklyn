package dev.fabled.frameworks.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.fabled.domain.repository.preferences.AppPreferencesRepository
import dev.fabled.frameworks.utils.applicationDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class AppPreferencesRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AppPreferencesRepository {

    private val datastore = context.applicationDataStore

    private object PreferencesKeys {
        val launchStateKey = booleanPreferencesKey(name = "launch_state")

        val cuisinesKey = stringPreferencesKey(name = "cuisines")
        val dietsKey = stringPreferencesKey(name = "diets")
        val intolerancesKey = stringPreferencesKey(name = "intolerances")
    }

    override suspend fun persistLaunchState() {
        datastore.edit { preferences ->
            preferences[PreferencesKeys.launchStateKey] = false
        }
    }

    override suspend fun persistUserCuisines(cuisinesTags: String) {
        datastore.edit { preferences ->
            preferences[PreferencesKeys.cuisinesKey] = cuisinesTags
        }
    }

    override suspend fun persistUserDiets(dietsTags: String) {
        datastore.edit { preferences ->
            preferences[PreferencesKeys.dietsKey] = dietsTags
        }
    }

    override suspend fun persistUserIntolerances(intolerancesTags: String) {
        datastore.edit { preferences ->
            preferences[PreferencesKeys.intolerancesKey] = intolerancesTags
        }
    }

    override suspend fun getUserCuisinesTags(): String = datastore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { preferences ->
            preferences[PreferencesKeys.cuisinesKey].orEmpty()
        }
        .first()

    override suspend fun getUserDietsTags(): String = datastore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { preferences ->
            preferences[PreferencesKeys.dietsKey].orEmpty()
        }
        .first()

    override suspend fun getUserIntolerancesTags(): String = datastore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { preferences ->
            preferences[PreferencesKeys.intolerancesKey].orEmpty()
        }
        .first()

    override suspend fun isFirstLaunch(): Boolean = datastore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { preferences ->
            preferences[PreferencesKeys.launchStateKey] ?: true
        }
        .first()
}