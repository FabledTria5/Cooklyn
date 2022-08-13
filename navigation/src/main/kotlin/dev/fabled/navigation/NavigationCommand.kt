package dev.fabled.navigation

import androidx.navigation.NamedNavArgument

interface NavigationCommand {

    val arguments: List<NamedNavArgument>

    val route: String

    val inclusive: Boolean

    object NavigateBack : NavigationCommand {
        override val arguments: List<NamedNavArgument> = emptyList()

        override val route: String = ""

        override val inclusive: Boolean = false
    }

}