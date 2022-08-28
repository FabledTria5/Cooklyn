package dev.fabled.domain.use_cases.filters

import dev.fabled.domain.model.FilterItem
import dev.fabled.domain.repository.FiltersRepository
import dev.fabled.domain.repository.preferences.AppPreferencesRepository
import javax.inject.Inject

class GetIntolerances @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository,
    private val filtersRepository: FiltersRepository
) {

    suspend operator fun invoke(): List<FilterItem> {
        val intolerances = filtersRepository.getIntolerances()
        val savedIntolerancesString = appPreferencesRepository.getUserIntolerancesTags()

        if (savedIntolerancesString.isEmpty()) return intolerances

        val savedDietsIds = savedIntolerancesString.split(", ")
        return intolerances.map { filter ->
            if (filter.tag in savedDietsIds) filter.copy(isSelected = true)
            else filter
        }
    }

}