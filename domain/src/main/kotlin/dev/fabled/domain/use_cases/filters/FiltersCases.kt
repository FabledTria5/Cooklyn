package dev.fabled.domain.use_cases.filters

import javax.inject.Inject

data class FiltersCases @Inject constructor(
    val getDiets: GetDiets,
    val getCuisines: GetCuisines,
    val getIntolerances: GetIntolerances,
    val saveUserFilters: SaveUserFilters
)
