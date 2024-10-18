package com.hutapp.org.notes.hut.sudocucompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.navigation.AppNavGraph
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.navigation.Screens
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game.MyLazyGridForSudokuGameScreen
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_result.ScreenVictory
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.started_screen.StartedScreen
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.theme.SUdocuComposeTheme
import dagger.hilt.android.AndroidEntryPoint

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
                    val stateCellViewModel = cellViewModel.selectedCell.observeAsState()
                    Column {
                        AppNavGraph(
                            navController = navHostController,
                            screenStartedContent = {
                                StartedScreen(
                                    stateCellViewModel = stateCellViewModel,
                                    onButtonClickListener = {
                                        navHostController.navigate(Screens.Game.route)
                                    }
                                )
                            },
                            screenGameContent = {
                                MyLazyGridForSudokuGameScreen(
                                    stateCellViewModel = stateCellViewModel,
                                    onLaunchListenerUnselected = {
                                        cellViewModel.unselectedCell()
                                    },
                                    onBottomSheetDismissRequest = {
                                        cellViewModel.unselectedCell()
                                    },
                                    onCheckedIsHideSelected = { isHide ->
                                        cellViewModel.onOffHideSelected(isHide = isHide)
                                    },
                                    onCheckedIsShowErrorAnswer = { isShowError ->
                                        cellViewModel.isShowErrorAnswer(isShowError = isShowError)
                                    },
                                    onCheckIsShowAlmostAnswer = { isShowAlmostAnswer ->
                                        cellViewModel.onOffAlmostAnswer(isHow = isShowAlmostAnswer)
                                    },
                                    onCheckIsShowAllAnswerCorrect = { isShow ->
                                        cellViewModel.onOffCorrectAnswer(isShow = isShow)
                                    },
                                    onCheckIsShowAnimationHint = { isShowAnimationHint ->
                                        cellViewModel.onOffAnimationHint(isShowAnimationHint = isShowAnimationHint)
                                    },
                                    navigateOnScreenVictory = {
                                        navHostController.navigate(Screens.Victory.route)
                                    },
                                    onCellClickListener = { itemCell ->
                                        cellViewModel.selectedCell(
                                            itemCell = itemCell
                                        )
                                    },
                                    onNumButtonClickListener = { value ->
                                        cellViewModel.setValueInCell(value = value)
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
}