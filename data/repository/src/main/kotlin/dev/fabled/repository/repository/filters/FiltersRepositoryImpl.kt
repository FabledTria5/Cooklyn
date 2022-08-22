package dev.fabled.repository.repository.filters

import dev.fabled.domain.model.FilterItem
import dev.fabled.domain.repository.FiltersRepository
import javax.inject.Inject

class FiltersRepositoryImpl @Inject constructor() : FiltersRepository {

    override fun getCuisines(): List<FilterItem> = listOf(
        China,
        France,
        Germany,
        India,
        Italy,
        Japan,
        Korea,
        Mexico,
        Spain,
        Uk,
        USA,
        Vietnam
    )


    override fun getIntolerances(): List<FilterItem> =
        listOf(Dairy, Egg, Gluten, Grain, Peanut, Seafood, Soy, TreeNut, Wheat)

    override fun getDiets(): List<FilterItem> =
        listOf(Ketogenic, Vegetarian, LactoseVegetarian, Perscetarian)

}