package dev.fabled.cooklyn.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.fabled.authorization_feature.screens.AuthorizationScreen
import dev.fabled.navigation.nav_directions.AuthorizationDirections

@ExperimentalAnimationApi
fun NavGraphBuilder.authorizationGraph() = navigation(
    startDestination = AuthorizationDirections.authorization.route,
    route = "authorization_graph"
) {

    composable(
        route = AuthorizationDirections.authorization.route
    ) {
        AuthorizationScreen(
            authorizationViewModel = hiltViewModel()
        )
    }

}