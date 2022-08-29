package dev.fabled.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import dev.fabled.database.entity.RecipeEntity
import dev.fabled.database.entity.refs.DishOfTheDayRef
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface RecipesDao {

    @Insert
    suspend fun insertDishOfTheDay(dishOfTheDayRef: DishOfTheDayRef)

    @Insert
    suspend fun insertRecipe(recipeEntity: RecipeEntity)

    @Query(value = "DELETE FROM dish_of_the_day_table")
    suspend fun removeDishOfTheDayRef()

    @Query(value = "DELETE FROM recipes_table WHERE recipe_id IN (SELECT recipe_id FROM dish_of_the_day_table)")
    fun removeDishOfTheDay()

    @Query(value = "SELECT created_at FROM dish_of_the_day_table")
    suspend fun getDailyRecipeRef(): LocalDate?

    @Query(
        value = "SELECT recipes_table.* FROM recipes_table " +
                "INNER JOIN dish_of_the_day_table " +
                "ON dish_of_the_day_table.recipe_id = recipes_table.recipe_id"
    )
    fun getRecipeOfTheDay(): Flow<RecipeEntity?>

    @Transaction
    suspend fun persistDishOfTheDay(recipeEntity: RecipeEntity) {
        removeDishOfTheDay()
        removeDishOfTheDayRef()

        val dishReference = DishOfTheDayRef(recipeId = recipeEntity.recipeId)
        insertDishOfTheDay(dishReference)
        insertRecipe(recipeEntity)
    }

}