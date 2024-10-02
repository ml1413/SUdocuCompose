package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.navigation

sealed class Screens(val route: String) {
    object Game : Screens(route = ROUTE_GAME)
    object Victory : Screens(route = ROUTE_VICTORY)
    object Started :Screens(route = ROUTE_STARTED)
    private companion object {
        const val ROUTE_GAME = "game"
        const val ROUTE_VICTORY = "victory"
        const val ROUTE_STARTED = "started"
    }
}