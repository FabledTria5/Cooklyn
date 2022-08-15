package dev.fabled.domain.use_cases.startup

import dev.fabled.domain.repository.preferences.AppPreferencesRepository
import javax.inject.Inject

class SaveLaunchState @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) {

    suspend operator fun invoke() = appPreferencesRepository.persistLaunchState()

}