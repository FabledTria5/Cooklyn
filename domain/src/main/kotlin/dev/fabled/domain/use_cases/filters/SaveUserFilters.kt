package dev.fabled.domain.use_cases.filters

import dev.fabled.domain.model.FilterItem
import dev.fabled.domain.repository.preferences.AppPreferencesRepository
import dev.fabled.domain.utils.ifNotEmpty
import javax.inject.Inject

class SaveUserFilters @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) {

    suspend operator fun invoke(
        cuisinesList: List<FilterItem>,
        dietsList: List<FilterItem>,
        intolerancesList: List<FilterItem>
    ) {
        cuisinesList
            .filter { it.isSelected }
            .joinToString { it.tag }
            .ifNotEmpty(appPreferencesRepository::persistUserCuisines)

        dietsList
            .filter { it.isSelected }
            .joinToString() { it.tag }
            .ifNotEmpty(appPreferencesRepository::persistUserDiets)

        intolerancesList
            .filter { it.isSelected }
            .joinToString() { it.tag }
            .ifNotEmpty(appPreferencesRepository::persistUserIntolerances)

    }

}