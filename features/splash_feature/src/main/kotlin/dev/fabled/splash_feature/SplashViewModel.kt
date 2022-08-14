package dev.fabled.splash_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fabled.domain.use_cases.startup.GetLaunchState
import dev.fabled.navigation.NavigationManager
import dev.fabled.navigation.nav_directions.AuthorizationDirections
import dev.fabled.navigation.nav_directions.OnBoardingDirections
import dev.fabled.navigation.nav_directions.PrimaryAppDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val readLaunchState: GetLaunchState
) : ViewModel() {

    init {
        checkFirstLaunch()
    }

    private fun checkFirstLaunch() = readLaunchState()
        .onEach { isFirstLaunch ->
            if (isFirstLaunch) {
                delay(timeMillis = 1000)
                openOnBoarding()
            } else {
                delay(timeMillis = 500)
            }
        }
        .launchIn(viewModelScope)

    private fun openOnBoarding() = navigationManager.navigate(OnBoardingDirections.onBoarding)

    private fun openAuthorizationScreen() =
        navigationManager.navigate(AuthorizationDirections.authorization)

    private fun openHomeScreen() = navigationManager.navigate(PrimaryAppDirections.home)

}