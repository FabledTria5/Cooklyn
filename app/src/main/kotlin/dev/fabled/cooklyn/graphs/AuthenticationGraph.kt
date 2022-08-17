package dev.fabled.cooklyn.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.fabled.authorization_feature.screens.AuthorizationScreen
import dev.fabled.authorization_feature.screens.RecommendationsScreen
import dev.fabled.navigation.nav_directions.AuthorizationDirections

@ExperimentalAnimationApi
fun NavGraphBuilder.authenticationGraph() {
    navigation(
        route = "authentication_graph",
        startDestination = AuthorizationDirections.authorization.route
    ) {
        composable(
            route = AuthorizationDirections.authorization.route,
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                    targetOffsetX = { fullWidth -> -fullWidth }
                )
            }
        ) {
            AuthorizationScreen(
                authorizationViewModel = hiltViewModel()
            )
        }
        composable(route = AuthorizationDirections.recommendations.route) {
            RecommendationsScreen()
        }
    }
}