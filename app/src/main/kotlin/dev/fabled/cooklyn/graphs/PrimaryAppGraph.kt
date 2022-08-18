package dev.fabled.cooklyn.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.fabled.home_feature.screens.HomeScreen
import dev.fabled.navigation.nav_directions.PrimaryAppDirections

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.primaryGraph() = navigation(
    startDestination = PrimaryAppDirections.home.route,
    route = "primary_graph"
) {

    composable(route = PrimaryAppDirections.home.route) {
        HomeScreen(homeViewModel = hiltViewModel())
    }

}