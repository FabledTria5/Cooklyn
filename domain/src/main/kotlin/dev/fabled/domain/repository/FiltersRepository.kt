package dev.fabled.domain.repository

import dev.fabled.domain.model.FilterItem

interface FiltersRepository {

    fun getCuisines(): List<FilterItem>

    fun getIntolerances(): List<FilterItem>

    fun getDiets(): List<FilterItem>

}