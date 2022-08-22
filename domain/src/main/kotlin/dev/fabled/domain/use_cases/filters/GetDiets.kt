package dev.fabled.domain.use_cases.filters

import dev.fabled.domain.model.FilterItem
import dev.fabled.domain.repository.FiltersRepository
import dev.fabled.domain.repository.preferences.AppPreferencesRepository
import javax.inject.Inject

class GetDiets @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository,
    private val filtersRepository: FiltersRepository
) {

    suspend operator fun invoke(): List<FilterItem> {
        val dietsList = filtersRepository.getDiets()
        val savedDietsString = appPreferencesRepository.getUserDietsTags()

        if (savedDietsString.isEmpty()) return dietsList

        val savedDietsIds = savedDietsString.split(", ")
        return dietsList.map { filter ->
            if (filter.tag in savedDietsIds) filter.copy(isSelected = true)
            else filter
        }
    }

}