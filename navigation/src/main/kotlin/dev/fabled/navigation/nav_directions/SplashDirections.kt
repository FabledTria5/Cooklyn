package dev.fabled.navigation.nav_directions

import androidx.navigation.NamedNavArgument
import dev.fabled.navigation.NavigationCommand

object SplashDirections {

    val splash = object : NavigationCommand {

        override val arguments: List<NamedNavArgument> = emptyList()

        override val route: String = "splash_screen"

        override val inclusive: Boolean = true

    }

}