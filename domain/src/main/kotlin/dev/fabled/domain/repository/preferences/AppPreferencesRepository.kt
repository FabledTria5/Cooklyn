package dev.fabled.domain.repository.preferences

import kotlinx.coroutines.flow.Flow

interface AppPreferencesRepository {

    suspend fun persistLaunchState()

    val isFirstLaunch: Flow<Boolean>

}