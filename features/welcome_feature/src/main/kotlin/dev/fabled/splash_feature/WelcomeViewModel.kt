package dev.fabled.splash_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fabled.domain.use_cases.startup.GetAuthenticationState
import dev.fabled.domain.use_cases.startup.GetLaunchState
import dev.fabled.navigation.NavigationManager
import dev.fabled.navigation.nav_directions.AuthorizationDirections
import dev.fabled.navigation.nav_directions.PrimaryAppDirections
import dev.fabled.navigation.nav_directions.WelcomeDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val readLaunchState: GetLaunchState,
    private val saveLaunchState: GetLaunchState,
    private val getAuthenticationState: GetAuthenticationState
) : ViewModel() {

    init {
        checkLaunchState()
    }

    private fun checkLaunchState() = viewModelScope.launch {
        val isFirstLaunch = readLaunchState()
        val isAuthenticated = getAuthenticationState()

        if (isFirstLaunch && !isAuthenticated) {
            openOnBoarding()
            return@launch
        }

        if (isAuthenticated) {
            openHomeScreen()
            return@launch
        }

        openAuthorizationScreen()
    }

    private fun openOnBoarding() =
        navigationManager.navigate(WelcomeDirections.onBoarding)

    private fun openAuthorizationScreen() =
        navigationManager.navigate(AuthorizationDirections.authorization)

    private fun openHomeScreen() =
        navigationManager.navigate(PrimaryAppDirections.HomeDirections.home)

    fun onBoardingCompleted() = viewModelScope.launch {
        saveLaunchState()
        navigationManager.navigate(AuthorizationDirections.authorization)
    }

}