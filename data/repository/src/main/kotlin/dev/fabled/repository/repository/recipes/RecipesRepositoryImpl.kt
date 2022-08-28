package dev.fabled.repository.repository.recipes

import dev.fabled.database.dao.RecipesDao
import dev.fabled.domain.model.RecipeItem
import dev.fabled.domain.repository.recipes.RecipesRepository
import dev.fabled.network.RecipesApi
import dev.fabled.repository.mappers.toDailyRecipe
import dev.fabled.repository.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import java.time.Duration
import java.time.LocalDate
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesApi: RecipesApi,
    private val recipesDao: RecipesDao
) : RecipesRepository {

    override suspend fun fetchDailyRecipe(recipeTags: String?) {
        val dailyRecipeRef = recipesDao.getDailyRecipeRef()
            ?: LocalDate.now().minusDays(1)

        if (Duration.between(
                dailyRecipeRef.atStartOfDay(),
                LocalDate.now().atStartOfDay()
            ).toDays() >= 1
        ) {
            val apiResponse = recipesApi.getRandomRecipes(number = 1, tags = recipeTags)

            if (apiResponse.isSuccessful && apiResponse.body() != null) {
                recipesDao.persistDishOfTheDay(apiResponse.body()!!.toEntity())
            } else {
                Timber.e(message = apiResponse.message())
            }
        }
    }

    override fun getDailyRecipe(): Flow<RecipeItem?> =
        recipesDao.getRecipeOfTheDay().toDailyRecipe()

}