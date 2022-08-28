package dev.fabled.domain.repository.recipes

import dev.fabled.domain.model.RecipeItem
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun fetchDailyRecipe(recipeTags: String?)

    fun getDailyRecipe(): Flow<RecipeItem?>

}