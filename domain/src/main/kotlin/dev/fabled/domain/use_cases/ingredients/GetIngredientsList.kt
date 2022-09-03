package dev.fabled.domain.use_cases.ingredients

import dev.fabled.domain.repository.recipes.IngredientsRepository
import javax.inject.Inject

class GetIngredientsList @Inject constructor(
    private val ingredientsRepository: IngredientsRepository
) {

    suspend operator fun invoke() = ingredientsRepository.getIngredientsList()

}