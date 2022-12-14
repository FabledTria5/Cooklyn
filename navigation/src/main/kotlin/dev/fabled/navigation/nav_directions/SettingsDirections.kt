package dev.fabled.navigation.nav_directions

import androidx.navigation.NamedNavArgument
import dev.fabled.navigation.NavigationCommand

object SettingsDirections {

    val recommendations = object : NavigationCommand {

        override val arguments: List<NamedNavArgument> = emptyList()

        override val route: String = "recommendations_screen"

        override val inclusive: Boolean = true

    }

}