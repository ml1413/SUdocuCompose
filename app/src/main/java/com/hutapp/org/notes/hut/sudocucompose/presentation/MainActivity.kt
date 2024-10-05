package com.hutapp.org.notes.hut.sudocucompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.navigation.AppNavGraph
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.navigation.Screens
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game.MyLazyGridForSudokuGameScreen
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_result.ScreenVictory
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.started_screen.StartedScreen
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.theme.SUdocuComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
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
                    val cellViewModel: CellViewModel by viewModels()
                    AppNavGraph(
                        navController = navHostController,
                        screenStartedContent = {
                            StartedScreen(
                                cellViewModel = cellViewModel,
                                onCheckedIsHideSelected = { isHide ->
                                    cellViewModel.onOffHideSelected(isHide = isHide)
                                }, onCheckedIsShowErrorAnswer = { isShowError ->
                                    cellViewModel.isShowErrorAnswer(isShowError = isShowError)
                                },
                                onButtonClickListener = {
                                    navHostController.navigate(Screens.Game.route)
                                }
                            )
                        },
                        screenGameContent = {
                            MyLazyGridForSudokuGameScreen(
                                cellViewModel = cellViewModel,// todo need refactor
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

