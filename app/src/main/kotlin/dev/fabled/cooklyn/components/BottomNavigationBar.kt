package dev.fabled.cooklyn.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.fabled.common.ui.Active
import dev.fabled.navigation.PrimaryNavigationDestinations

@Composable
fun BottomNavigationBar(navController: NavHostController, currentDestination: String) {
    val bottomNavigationDestinations = PrimaryNavigationDestinations.getDestinations()

    val isBottomBarEnabled = bottomNavigationDestinations.any {
        it.command.route == currentDestination
    }

    val bottomBarHeight by animateDpAsState(
        targetValue = if (isBottomBarEnabled) 80.dp else 0.dp,
        animationSpec = tween(delayMillis = 1000)
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(bottomBarHeight),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        bottomNavigationDestinations.forEach { destination ->
            val isSelected = currentDestination == destination.command.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!navController.popBackStack(
                            route = destination.command.route,
                            inclusive = false
                        )
                    ) {
                        navController.navigate(destination.command.route)
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected)
                            destination.selectedIcon
                        else
                            destination.unselectedIcon,
                        contentDescription = "Open ${destination.command.route}",
                        modifier = Modifier.size(30.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Active,
                    unselectedIconColor = Color.LightGray,
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }

}

@Preview
@Composable
fun Preview() {
    BottomNavigationBar(
        navController = NavHostController(LocalContext.current),
        currentDestination = "home_screen"
    )
}