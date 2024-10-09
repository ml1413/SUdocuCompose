package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hutapp.org.notes.hut.sudocucompose.presentation.CellViewModel
import kotlinx.coroutines.delay


@Composable
fun MyLazyGridForSudokuGameScreen(
    modifier: Modifier = Modifier,
    cellViewModel: CellViewModel,
    onBottomSheetDismissRequest: () -> Unit,
    navigateOnScreenVictory: () -> Unit,
    onCheckedIsHideSelected: (check: Boolean) -> Unit,
    onCheckedIsShowErrorAnswer: (check: Boolean) -> Unit,
    onCheckIsShowAlmostAnswer: (Boolean) -> Unit,
    onCheckIsShowAllAnswerCorrect: (Boolean) -> Unit,
) {
    val sudokuViewModelState = cellViewModel.selectedCell.observeAsState().value
    if (sudokuViewModelState is CellViewModel.GameState.ResumeGame) {
        // hide selected after 20 t and seconds_____________________________________________________
        HideSelected(stateFromViewModel = sudokuViewModelState, cellViewModel = cellViewModel)
        //__________________________________________________________________________________________
        val openBottomSheet = rememberSaveable { mutableStateOf(false) }
        val colorGrid = MaterialTheme.colorScheme.onBackground
        val shape12Dp = RoundedCornerShape(12.dp, 12.dp, 16.dp, 16.dp)
        Log.d("TAG1", "MyLazyGridForSudokuGameScreen: ")
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .wrapContentSize()
//                    .background(
//                        MaterialTheme.colorScheme.onBackground,
//                        shape12Dp
//                    )
                    .border(
                        width = 1.dp,
                        color = colorGrid,
                        shape = shape12Dp
                    )
            ) {

                SudokuTableGrid(
                    modifier = modifier,
                    stateFromViewModel = sudokuViewModelState,
                    colorGrid = colorGrid,
                    onCellClickListener = { itemCell ->
                        cellViewModel.selectedCell(
                            itemCell = itemCell
                        )
                    }
                )
                MyBottomKeyBoard(
                    modifier = modifier,
                    onNumButtonClickListener = { value ->
                        cellViewModel.setValueInCell(value = value)
                    }
                )

            }
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(onClick = {
                    openBottomSheet.value = openBottomSheet.value.not()
                }) {
                    Icon(
                        modifier = modifier,
                        imageVector = Icons.Default.Settings, contentDescription = ""
                    )
                }
            }
            if (openBottomSheet.value) {
                MyBottomSheet(
                    sudokuViewModelState = sudokuViewModelState,
                    onDismissRequest = {
                        onBottomSheetDismissRequest()
                        openBottomSheet.value = false
                    },
                    onCheckedIsHideSelected = onCheckedIsHideSelected,
                    onCheckedIsShowErrorAnswer = onCheckedIsShowErrorAnswer,
                    onCheckIsShowAlmostAnswer = onCheckIsShowAlmostAnswer,
                    onCheckIsShowAllAnswerCorrect = onCheckIsShowAllAnswerCorrect
                )
            }
        }
    } else if (sudokuViewModelState is CellViewModel.GameState.Victory) {
        navigateOnScreenVictory()
    }
}


@Composable
private fun HideSelected(
    stateFromViewModel: CellViewModel.GameState.ResumeGame,
    cellViewModel: CellViewModel
) {
    if (stateFromViewModel.modelSudoku.isHideSelected && stateFromViewModel.modelSudoku.hasSelectedCells) {
        LaunchedEffect(stateFromViewModel) {
            delay(20_000)
            cellViewModel.unselectedCell()
        }
    }
}



