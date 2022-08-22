package dev.fabled.domain.repository.preferences

interface AppPreferencesRepository {

    suspend fun persistLaunchState()

    suspend fun persistUserCuisines(cuisinesTags: String)

    suspend fun persistUserDiets(dietsTags: String)

    suspend fun persistUserIntolerances(intolerancesTags: String)

    suspend fun isFirstLaunch(): Boolean

    suspend fun getUserCuisinesTags(): String

    suspend fun getUserDietsTags(): String

    suspend fun getUserIntolerancesIds(): String

}