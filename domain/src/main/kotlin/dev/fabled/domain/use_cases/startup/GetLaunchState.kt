package dev.fabled.domain.use_cases.startup

import dev.fabled.domain.repository.preferences.AppPreferencesRepository
import javax.inject.Inject

class GetLaunchState @Inject constructor(
    private val preferencesRepository: AppPreferencesRepository
) {

    suspend operator fun invoke() = preferencesRepository.isFirstLaunch()

}