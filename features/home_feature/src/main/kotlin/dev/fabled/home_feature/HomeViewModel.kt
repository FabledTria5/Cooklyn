package dev.fabled.home_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fabled.domain.model.IngredientItem
import dev.fabled.domain.model.RecipeItem
import dev.fabled.domain.model.Resource
import dev.fabled.domain.repository.home.GetUserName
import dev.fabled.domain.use_cases.ingredients.GetIngredientsList
import dev.fabled.domain.use_cases.recipes.FetchDailyRecipe
import dev.fabled.domain.use_cases.recipes.GetDailyRecipe
import dev.fabled.navigation.NavigationCommand
import dev.fabled.navigation.NavigationManager
import dev.fabled.navigation.nav_directions.PrimaryAppDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUserName: GetUserName,
    private val navigationManager: NavigationManager,
    private val getDailyRecipe: GetDailyRecipe,
    private val fetchDailyRecipe: FetchDailyRecipe,
    private val getIngredientsList: GetIngredientsList
) : ViewModel() {

    var userName by mutableStateOf(value = "")
        private set

    var dailyRecipe by mutableStateOf<Resource<RecipeItem>>(Resource.Loading)
        private set

    private val _selectedIngredients = mutableStateListOf<IngredientItem>()
    val selectedIngredients: List<IngredientItem> = _selectedIngredients

    private val _ingredientsList = mutableStateListOf<IngredientItem>()
    val ingredientsList: List<IngredientItem> = _ingredientsList

    init {
        userName = getUserName()
        viewModelScope.launch(Dispatchers.IO) { fetchDailyRecipe() }
        loadDailyRecipe()
    }

    fun onAddIngredientsClicked() {
        navigationManager.navigate(PrimaryAppDirections.HomeDirections.ingredients)

        viewModelScope.launch(Dispatchers.IO) {
            _ingredientsList.addAll(getIngredientsList())
        }
    }

    fun onIngredientSelected(ingredientItem: IngredientItem) {
        _selectedIngredients.add(ingredientItem)
        _ingredientsList.remove(ingredientItem)
    }

    fun onIngredientsBackClicked() {
        navigationManager.navigate(NavigationCommand.NavigateBack)
    }

    fun onRecipeClicked(recipeId: Int) {

    }

    fun onSearchClicked(searchQuery: String) {

    }

    private fun loadDailyRecipe() = getDailyRecipe()
        .flowOn(Dispatchers.IO)
        .onEach { result ->
            dailyRecipe = result
        }
        .launchIn(viewModelScope)


}