package dev.fabled.cooklyn.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.fabled.authorization_feature.screens.AuthorizationScreen
import dev.fabled.cooklyn.components.BottomNavigationBar
import dev.fabled.cooklyn.graphs.primaryGraph
import dev.fabled.navigation.NavigationCommand
import dev.fabled.navigation.NavigationManager
import dev.fabled.navigation.nav_directions.AuthorizationDirections
import dev.fabled.navigation.nav_directions.SettingsDirections
import dev.fabled.navigation.nav_directions.WelcomeDirections
import dev.fabled.recommendations_feature.screens.RecommendationsScreen
import dev.fabled.splash_feature.screens.OnBoardingScreen
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
fun PrimaryNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)

    AnimatedNavHost(
        navController = navController,
        modifier = modifier,
        startDestination = WelcomeDirections.splash.route
    ) {
        composable(route = WelcomeDirections.splash.route) {
            SplashScreen(
                welcomeViewModel = hiltViewModel(viewModelStoreOwner = viewModelStoreOwner)
            )
        }
        composable(
            route = WelcomeDirections.onBoarding.route,
            exitTransition = {
                slideOutVertically(
                    animationSpec = tween(durationMillis = 1000),
                    targetOffsetY = { fullHeight -> -fullHeight }
                )
            }
        ) {
            OnBoardingScreen(
                welcomeViewModel = hiltViewModel(viewModelStoreOwner = viewModelStoreOwner)
            )
        }
        composable(
            route = AuthorizationDirections.authorization.route,
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(durationMillis = 1000),
                    targetOffsetX = { fullWidth -> -fullWidth })
            }
        ) {
            AuthorizationScreen(
                authorizationViewModel = hiltViewModel()
            )
        }
        composable(
            route = SettingsDirections.recommendations.route,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(durationMillis = 1000),
                    initialOffsetX = { fullWidth -> fullWidth })
            }
        ) {
            RecommendationsScreen(recommendationsViewModel = hiltViewModel())
        }
        primaryGraph()
    }
}