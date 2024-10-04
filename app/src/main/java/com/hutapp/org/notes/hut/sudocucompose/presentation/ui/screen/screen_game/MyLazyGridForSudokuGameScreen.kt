package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hutapp.org.notes.hut.sudocucompose.presentation.CellViewModel
import kotlinx.coroutines.delay


@Composable
fun MyLazyGridForSudokuGameScreen(
    modifier: Modifier = Modifier,
    cellViewModel: CellViewModel,
    navigateOnScreenVictory: () -> Unit
) {
    val sudokuViewModelState = cellViewModel.selectedCell.observeAsState().value
    if (sudokuViewModelState is CellViewModel.GameState.ResumeGame) {
        // hide selected after 20 t and seconds_____________________________________________________
        HideSelected(stateFromViewModel = sudokuViewModelState, cellViewModel = cellViewModel)
        //__________________________________________________________________________________________
        val colorGrid = MaterialTheme.colorScheme.onBackground
        Log.d("TAG1", "MyLazyGridForSudokuGameScreen: ")
        Box(
            modifier = modifier
                .wrapContentSize()
                .padding(16.dp)
                .border(
                    width = 1.dp,
                    color = colorGrid,
                    shape = RoundedCornerShape(12.dp, 12.dp, 16.dp, 16.dp)
                )
        ) {
            Column() {
                SudokuTableGrid(
                    modifier = modifier,
                    stateFromViewModel = sudokuViewModelState,
                    colorGrid = colorGrid,
                    onCellClickListener = { index, selectedRow, selectedColum, isSelected ->
                        cellViewModel.selectedCell(
                            index = index,
                            selectedRow = selectedRow,
                            selectedColum = selectedColum,
                            isSelected = isSelected
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