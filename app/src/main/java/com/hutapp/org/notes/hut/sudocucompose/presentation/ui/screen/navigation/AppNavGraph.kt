package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navController: NavHostController,
    screenStartedContent: @Composable () -> Unit,
    screenGameContent: @Composable () -> Unit,
    screenVictory: @Composable () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Started.route,
        builder = {
            composable(Screens.Started.route) {
                screenStartedContent()
            }
            composable(Screens.Game.route) {
                screenGameContent()
            }
            composable(Screens.Victory.route) {
                screenVictory()
            }
        }
    )
}