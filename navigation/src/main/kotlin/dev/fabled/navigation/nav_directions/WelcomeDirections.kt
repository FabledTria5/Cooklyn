package dev.fabled.navigation.nav_directions

import androidx.navigation.NamedNavArgument
import dev.fabled.navigation.NavigationCommand

object WelcomeDirections {

    val splash = object : NavigationCommand {

        override val arguments: List<NamedNavArgument> = emptyList()

        override val route: String = "splash_screen"

        override val inclusive: Boolean = true

    }

    val onBoarding = object : NavigationCommand {

        override val arguments: List<NamedNavArgument> = emptyList()

        override val route: String = "on_boarding_screen"

        override val inclusive: Boolean = true

    }

}