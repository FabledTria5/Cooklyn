package dev.fabled.recommendations_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fabled.domain.model.FilterItem
import dev.fabled.domain.model.Resource
import dev.fabled.domain.use_cases.filters.FiltersCases
import dev.fabled.navigation.NavigationManager
import dev.fabled.navigation.nav_directions.PrimaryAppDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationsViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val filtersCases: FiltersCases
) : ViewModel() {

    private val _cuisinesList = mutableStateListOf<FilterItem>()
    val cuisinesList: List<FilterItem> = _cuisinesList

    private val _dietsList = mutableStateListOf<FilterItem>()
    val dietsList: List<FilterItem> = _dietsList

    private val _intolerancesList = mutableStateListOf<FilterItem>()
    val intolerancesList: List<FilterItem> = _intolerancesList

    var recommendationsScreenState by mutableStateOf<Resource<Nothing>>(Resource.Idle)

    init {
        loadRecommendations()
    }

    private fun loadRecommendations() = viewModelScope.launch {
        launch { _cuisinesList.addAll(filtersCases.getCuisines()) }
        launch { _dietsList.addAll(filtersCases.getDiets()) }
        launch { _intolerancesList.addAll(filtersCases.getIntolerances()) }
    }

    fun onCuisineSelected(filterItem: FilterItem) {
        val index = _cuisinesList.indexOf(filterItem)
        _cuisinesList[index] = filterItem.copy(isSelected = !filterItem.isSelected)
    }

    fun onDietSelected(filterItem: FilterItem) {
        val index = _dietsList.indexOf(filterItem)
        _dietsList[index] = filterItem.copy(isSelected = !filterItem.isSelected)
    }

    fun onIntoleranceSelected(filterItem: FilterItem) {
        val index = _intolerancesList.indexOf(filterItem)
        _intolerancesList[index] = filterItem.copy(isSelected = !filterItem.isSelected)
    }

    fun onFinishedRecommendations() {
        viewModelScope.launch {
            recommendationsScreenState = Resource.Loading

            filtersCases.saveUserFilters(cuisinesList, dietsList, intolerancesList)
            navigationManager.navigate(PrimaryAppDirections.HomeDirections.home)
        }
    }

}