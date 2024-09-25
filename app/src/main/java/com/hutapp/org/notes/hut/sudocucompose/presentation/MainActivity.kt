package com.hutapp.org.notes.hut.sudocucompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.navigation.AppNavGraph
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.navigation.Screens
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game.MyLazyGridForSudokuGameScreen
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_result.ScreenVictory
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.theme.SUdocuComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SUdocuComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHostController = rememberNavController()
                    val cellViewModel = viewModel<CellViewModel>()
                    AppNavGraph(
                        navController = navHostController,
                        screenGameContent = {
                            MyLazyGridForSudokuGameScreen(
                                cellViewModel = cellViewModel,
                                navigateOnScreenVictory = {
                                    navHostController.navigate(Screens.Victory.route)
                                })
                        },
                        screenVictory = {
                            ScreenVictory()
                        }
                    )
                }
            }
        }
    }
}
