package dev.fabled.domain.repository.recipes

import dev.fabled.domain.model.IngredientItem

interface IngredientsRepository {

    suspend fun getIngredientsList(): List<IngredientItem>

    suspend fun searchIngredients(query: String)

    suspend fun saveLastSearchedIngredients(ingredientItem: IngredientItem)

}