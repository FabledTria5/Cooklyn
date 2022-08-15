package dev.fabled.cooklyn.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.fabled.cooklyn.components.BottomNavigationBar
import dev.fabled.navigation.NavigationCommand
import dev.fabled.navigation.NavigationManager
import dev.fabled.navigation.nav_directions.AuthorizationDirections
import dev.fabled.navigation.nav_directions.OnBoardingDirections
import dev.fabled.navigation.nav_directions.SplashDirections
import dev.fabled.on_boarding_feature.screens.OnBoardingScreen
import dev.fabled.splash_feature.screens.SplashScreen
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navigationManager: NavigationManager) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = Color.White, darkIcons = true)

    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    var inclusiveScreen by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = navigationManager.commands) {
        navigationManager.commands.collectLatest { navigationCommand ->
            if (navigationCommand is NavigationCommand.NavigateBack) {
                navController.popBackStack()
                return@collectLatest
            }
            navController.navigate(navigationCommand.route) {
                if (inclusiveScreen) {
                    currentDestination?.let { route ->
                        popUpTo(route) { inclusive = inclusiveScreen }
                    }
                }
            }
            inclusiveScreen = navigationCommand.inclusive
        }
    }

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentDestination = currentDestination.orEmpty()
            )
        }
    ) { scaffoldPadding ->
        PrimaryNavigation(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = scaffoldPadding.calculateBottomPadding())
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun PrimaryNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    AnimatedNavHost(
        navController = navController,
        modifier = modifier,
        startDestination = SplashDirections.splash.route
    ) {
        composable(route = SplashDirections.splash.route) {
            SplashScreen(splashViewModel = hiltViewModel())
        }
        composable(route = OnBoardingDirections.onBoarding.route) {
            OnBoardingScreen(onBoardingViewModel = hiltViewModel())
        }
        composable(route = AuthorizationDirections.authorization.route) {

        }
    }
}