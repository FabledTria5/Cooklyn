package dev.fabled.repository.repository.recipes

import dev.fabled.database.dao.RecipesDao
import dev.fabled.domain.model.IngredientItem
import dev.fabled.domain.repository.preferences.AppPreferencesRepository
import dev.fabled.domain.repository.recipes.IngredientsRepository
import dev.fabled.repository.mappers.toIngredientItem
import javax.inject.Inject

class IngredientsRepositoryImpl @Inject constructor(
    private val recipesDao: RecipesDao,
    private val appPreferencesRepository: AppPreferencesRepository
) : IngredientsRepository {

    override suspend fun getIngredientsList() =
        recipesDao.getAllIngredients().map { it.toIngredientItem() }

    override suspend fun searchIngredients(query: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveLastSearchedIngredients(ingredientItem: IngredientItem) {
        TODO("Not yet implemented")
    }

}