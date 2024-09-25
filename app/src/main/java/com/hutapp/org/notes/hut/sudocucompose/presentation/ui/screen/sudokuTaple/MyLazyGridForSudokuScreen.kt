package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.sudokuTaple

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hutapp.org.notes.hut.sudocucompose.presentation.SelectedCellViewModel
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_result.ScreenResult


@Composable
fun MyLazyGridForSudokuScreen(
    modifier: Modifier = Modifier,
    cellViewModel: SelectedCellViewModel
) {
    val sudokuViewModelState =
        cellViewModel.selectedCell.observeAsState(SelectedCellViewModel.GameState.Initial)

    val stateFromViewModel = sudokuViewModelState.value
    if (stateFromViewModel is SelectedCellViewModel.GameState.ResumeGame) {
        val colorGrid = MaterialTheme.colorScheme.onBackground
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
                    stateFromViewModel = stateFromViewModel,
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
    } else {
        ScreenResult()
    }
}