package dev.fabled.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.ui.graphics.vector.ImageVector
import dev.fabled.navigation.nav_directions.PrimaryAppDirections

sealed class PrimaryNavigationDestinations(
    val command: NavigationCommand,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {

    object HomeDestination : PrimaryNavigationDestinations(
        command = PrimaryAppDirections.HomeDirections.home,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    object CookBookDestination : PrimaryNavigationDestinations(
        command = PrimaryAppDirections.cookbook,
        selectedIcon = Icons.Filled.Bookmark,
        unselectedIcon = Icons.Outlined.BookmarkBorder
    )

    object ScheduleDestination : PrimaryNavigationDestinations(
        command = PrimaryAppDirections.schedule,
        selectedIcon = Icons.Filled.Timer,
        unselectedIcon = Icons.Outlined.Timer
    )

    object ProfileDestination : PrimaryNavigationDestinations(
        command = PrimaryAppDirections.profile,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )

    companion object {
        fun getDestinations() =
            listOf(HomeDestination, CookBookDestination, ScheduleDestination, ProfileDestination)
    }
}