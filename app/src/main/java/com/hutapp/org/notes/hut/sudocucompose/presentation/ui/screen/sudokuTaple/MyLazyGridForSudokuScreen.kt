package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.sudokuTaple

import android.util.Log
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


@Composable
fun MyLazyGridForSudokuScreen(
    modifier: Modifier = Modifier,
    selectedCellViewModel: SelectedCellViewModel
) {
    val sudokuViewModelState =
        selectedCellViewModel.selectedCell.observeAsState(SelectedCellViewModel.GameState.Initial)
    val stateFromViewModel = sudokuViewModelState.value
    if (stateFromViewModel is SelectedCellViewModel.GameState.ResumeGame) {
        val colorGrid = MaterialTheme.colorScheme.onBackground
        Log.d("TAG1", "MyLazyGrid: ")
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
                    selectedCellViewModel = selectedCellViewModel
                )
                MyBottomKeyBoard(
                    modifier = modifier,
                    selectedCellViewModel = selectedCellViewModel
                )
            }
        }
    } else {

    }
}