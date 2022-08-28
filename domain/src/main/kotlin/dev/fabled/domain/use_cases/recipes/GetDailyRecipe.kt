package dev.fabled.domain.use_cases.recipes

import dev.fabled.domain.model.Resource
import dev.fabled.domain.repository.recipes.RecipesRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDailyRecipe @Inject constructor(
    private val recipesRepository: RecipesRepository
) {

    operator fun invoke() = recipesRepository.getDailyRecipe()
        .map { item ->
            if (item != null) Resource.Success(item)
            else Resource.Loading
        }

}