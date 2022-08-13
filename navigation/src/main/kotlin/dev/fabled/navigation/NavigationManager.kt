package dev.fabled.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigationManager {

    private val _commands = MutableSharedFlow<NavigationCommand>(replay = 1)
    val commands = _commands.asSharedFlow()

    fun navigate(direction: NavigationCommand) = _commands.tryEmit(direction)

}