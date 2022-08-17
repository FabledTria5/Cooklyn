package dev.fabled.on_boarding_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fabled.domain.use_cases.startup.SaveLaunchState
import dev.fabled.navigation.NavigationManager
import dev.fabled.navigation.nav_directions.AuthorizationDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val saveLaunchState: SaveLaunchState
): ViewModel() {

    fun onBoardingCompleted() = viewModelScope.launch {
        saveLaunchState()
        navigationManager.navigate(AuthorizationDirections.authorization)
    }

}