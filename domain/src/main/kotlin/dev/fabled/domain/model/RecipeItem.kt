package dev.fabled.domain.model

data class RecipeItem(
    val recipeId: Int = -1,
    val recipeImage: String? = null,
    val recipeName: String = "",
    val recipeTime: Int = 0,
    val recipeCuisine: String = "",
)
