package dev.fabled.domain.use_cases.recipes

import dev.fabled.domain.repository.preferences.AppPreferencesRepository
import dev.fabled.domain.repository.recipes.RecipesRepository
import javax.inject.Inject

class FetchDailyRecipe @Inject constructor(
    private val recipesRepository: RecipesRepository,
    private val appPreferencesRepository: AppPreferencesRepository
) {

    suspend operator fun invoke() {
        val tags = appPreferencesRepository.getUserDietsTags()
            .removeSuffix(", ")
            .lowercase()

        recipesRepository.fetchDailyRecipe(recipeTags = tags.ifEmpty { null })
    }

}