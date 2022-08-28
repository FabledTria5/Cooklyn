package dev.fabled.home_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fabled.domain.model.RecipeItem
import dev.fabled.domain.model.Resource
import dev.fabled.domain.repository.home.GetUserName
import dev.fabled.domain.use_cases.recipes.FetchDailyRecipe
import dev.fabled.domain.use_cases.recipes.GetDailyRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUserName: GetUserName,
    private val getDailyRecipe: GetDailyRecipe,
    private val fetchDailyRecipe: FetchDailyRecipe
) : ViewModel() {

    var userName by mutableStateOf(value = "")
        private set

    var dailyRecipe by mutableStateOf<Resource<RecipeItem>>(Resource.Loading)
        private set

    init {
        userName = getUserName()

        viewModelScope.launch(Dispatchers.IO) { fetchDailyRecipe() }

        loadDailyRecipe()
    }

    private fun loadDailyRecipe() = getDailyRecipe()
        .flowOn(Dispatchers.IO)
        .onEach { result ->
            dailyRecipe = result
        }
        .launchIn(viewModelScope)

    fun onRecipeClicked(recipeId: Int) {

    }

    fun onSearchClicked(searchQuery: String) {

    }


}