package dev.fabled.repository.mappers

import dev.fabled.database.entity.RecipeEntity
import dev.fabled.network.dto.random_recipe.RandomRecipesResponse

fun RandomRecipesResponse.toEntity(): RecipeEntity {
    val targetRecipe = recipes.first()

    return RecipeEntity(
        recipeId = targetRecipe.id,
        recipeName = targetRecipe.title,
        recipeImage = targetRecipe.image,
        recipeTime = targetRecipe.readyInMinutes,
        servings = targetRecipe.servings
    )
}