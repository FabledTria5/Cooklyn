package dev.fabled.repository.mappers

import dev.fabled.database.entity.RecipeEntity
import dev.fabled.domain.model.RecipeItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<RecipeEntity?>.toDailyRecipe() = map { entity ->
    if (entity != null) {
        RecipeItem(
            recipeId = entity.recipeId,
            recipeImage = entity.recipeImage,
            recipeName = entity.recipeName,
            recipeTime = entity.recipeTime,
        )
    } else null

}