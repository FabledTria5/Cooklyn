package dev.fabled.cooklyn.graphs

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.fabled.home_feature.screens.HomeScreen
import dev.fabled.home_feature.screens.IngredientsScreen
import dev.fabled.navigation.nav_directions.PrimaryAppDirections

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.primaryGraph(viewModelStoreOwner: ViewModelStoreOwner) = navigation(
    startDestination = PrimaryAppDirections.HomeDirections.home.route,
    route = "primary_graph"
) {

    composable(
        route = PrimaryAppDirections.HomeDirections.home.route,
        exitTransition = {
            fadeOut(animationSpec = tween(durationMillis = 400))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(durationMillis = 400))
        }
    ) {
        HomeScreen(homeViewModel = hiltViewModel(viewModelStoreOwner = viewModelStoreOwner))
    }

    composable(
        route = PrimaryAppDirections.HomeDirections.ingredients.route,
        enterTransition = {
            slideInVertically(
                animationSpec = tween(durationMillis = 1000),
                initialOffsetY = { fullHeight -> -fullHeight })
        },
        popExitTransition = {
            slideOutVertically(
                animationSpec = tween(durationMillis = 1000),
                targetOffsetY = { fullHeight -> -fullHeight })
        }
    ) {
        IngredientsScreen(homeViewModel = hiltViewModel(viewModelStoreOwner = viewModelStoreOwner))
    }

}