package dev.fabled.navigation.nav_directions

import androidx.navigation.NamedNavArgument
import dev.fabled.navigation.NavigationCommand

object PrimaryAppDirections {

    val home = object : NavigationCommand {

        override val arguments: List<NamedNavArgument> = emptyList()

        override val route: String = "home_screen"

        override val inclusive: Boolean = false

    }

    val cookbook = object : NavigationCommand {

        override val arguments: List<NamedNavArgument> = emptyList()

        override val route: String = "cookbook_screen"

        override val inclusive: Boolean = false

    }

    val schedule = object : NavigationCommand {

        override val arguments: List<NamedNavArgument> = emptyList()

        override val route: String = "schedule_screen"

        override val inclusive: Boolean = false

    }

    val profile = object : NavigationCommand {

        override val arguments: List<NamedNavArgument> = emptyList()

        override val route: String = "profile_screen"

        override val inclusive: Boolean = false

    }

}