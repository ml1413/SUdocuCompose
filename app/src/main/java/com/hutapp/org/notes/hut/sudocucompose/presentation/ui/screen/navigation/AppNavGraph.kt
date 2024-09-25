package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    screenGameContent: @Composable () -> Unit,
    screenVictory: @Composable () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Game.route,
        builder = {
            composable(Screens.Game.route) {
                screenGameContent()
            }
            composable(Screens.Victory.route) {
                screenVictory()
            }
        }
    )
}