package dev.fabled.network

import dev.fabled.network.dto.random_recipe.RandomRecipesResponse
import dev.fabled.network.dto.recipes_list.RecipesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesApi {

    @GET(value = "recipes/complexSearch")
    suspend fun getRecipes(
        @Query(value = "apiKey") key: String = BuildConfig.API_KEY,
        @Query(value = "query") query: String,
        @Query(value = "cuisine") cuisine: String? = null,
        @Query(value = "diet") diet: String? = null,
        @Query(value = "intolerances") intolerances: String? = null,
        @Query(value = "includeIngredients") ingredients: String? = null,
    ): Response<RecipesListResponse>

    @GET(value = "recipes/random")
    suspend fun getRandomRecipes(
        @Query(value = "apiKey") key: String = BuildConfig.API_KEY,
        @Query(value = "tags") tags: String?,
        @Query(value = "number") number: Int
    ): Response<RandomRecipesResponse>

    @GET(value = "recipes/{id}/information")
    suspend fun getRecipeInfo(
        @Path(value = "id") recipeId: Int,
        @Query(value = "apiKey") key: String = BuildConfig.API_KEY
    )
}