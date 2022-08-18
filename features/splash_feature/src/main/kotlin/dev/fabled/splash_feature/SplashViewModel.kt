package dev.fabled.splash_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fabled.domain.model.Resource
import dev.fabled.domain.use_cases.authorization.IsUSerAuthenticated
import dev.fabled.domain.use_cases.startup.GetLaunchState
import dev.fabled.navigation.NavigationManager
import dev.fabled.navigation.nav_directions.AuthorizationDirections
import dev.fabled.navigation.nav_directions.OnBoardingDirections
import dev.fabled.navigation.nav_directions.PrimaryAppDirections
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val readLaunchState: GetLaunchState,
    private val isUSerAuthenticated: IsUSerAuthenticated,
) : ViewModel() {

    init {
        checkLaunchState()
    }

    private fun checkLaunchState() = readLaunchState()
        .combine(isUSerAuthenticated()) { isFirstLaunch, isAuthenticated ->
            isFirstLaunch to isAuthenticated
        }.onEach {
            if (it.first) openOnBoarding()
            else {
                when (val isAuthenticated = it.second) {
                    is Resource.Success -> {
                        if (!isAuthenticated.data) openAuthorizationScreen()
                        else openHomeScreen()
                    }
                    else -> Unit
                }
            }
        }
        .launchIn(viewModelScope)

    private fun openOnBoarding() =
        navigationManager.navigate(OnBoardingDirections.onBoarding)

    private fun openAuthorizationScreen() =
        navigationManager.navigate(AuthorizationDirections.authorization)

    private fun openHomeScreen() = navigationManager.navigate(PrimaryAppDirections.home)

}