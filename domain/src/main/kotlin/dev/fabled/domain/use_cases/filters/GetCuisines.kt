package dev.fabled.domain.use_cases.filters

import dev.fabled.domain.model.FilterItem
import dev.fabled.domain.repository.FiltersRepository
import dev.fabled.domain.repository.preferences.AppPreferencesRepository
import javax.inject.Inject

class GetCuisines @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository,
    private val filtersRepository: FiltersRepository
) {

    suspend operator fun invoke(): List<FilterItem> {
        val cuisines = filtersRepository.getCuisines()
        val savedCuisinesString = appPreferencesRepository.getUserCuisinesTags()

        if (savedCuisinesString.isEmpty()) return cuisines

        val savedDietsIds = savedCuisinesString.split(", ")
        return cuisines.map { filter ->
            if (filter.tag in savedDietsIds) filter.copy(isSelected = true)
            else filter
        }
    }

}