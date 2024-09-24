package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.sudokuTaple

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hutapp.org.notes.hut.sudocucompose.presentation.SelectedCellViewModel

// sudoku table ________________________________________________________________________________
@Composable
fun SudokuTableGrid(
    modifier: Modifier,
    stateFromViewModel: SelectedCellViewModel.GameState.ResumeGame,
    colorGrid: Color,
    selectedCellViewModel: SelectedCellViewModel
) {
    var index = 0
    val selectedCell = stateFromViewModel.list.firstOrNull { it.isSelected }
    Card(
        border = BorderStroke(1.dp, colorGrid),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            // grand cells grid ___________________________________________________________________
            Column(modifier = modifier.aspectRatio(1f)) {
                for (colum in 1..3) {
                    Row {
                        for (row in 1..3) {
                            Box(
                                modifier = modifier
                                    .weight(1f)
                                    .border(
                                        width = 1.dp,
                                        color = colorGrid
                                    )
                                    .aspectRatio(1f)
                                    .background(
                                        color = getColorBackgroundGrandGrid(
                                            selectedCell,
                                            row,
                                            colum,
                                        )
                                    )
                            )
                        }
                    }
                }
            }
            //grid for number_______________________________________________________________________
            Column(modifier = modifier.aspectRatio(1f)) {
                for (colum in 1..9) {
                    Row(modifier = modifier.weight(1f)) {
                        for (row in 1..9) {
                            val itemModelSudoku = stateFromViewModel.list.get(index)
                            Box(
                                modifier = modifier
                                    .weight(1f)
                                    .fillMaxSize()
                                    .background(
                                        getColorBoxBackground(
                                            itemModelSudoku,
                                            selectedCell,
                                            index,
                                            row,
                                            colum,
                                        )
                                    )
                                    .border(
                                        width = 0.1.dp,
                                        color = colorGrid
                                    )
                                    .clickable(
                                        enabled = itemModelSudoku.isStartedCell.not()
                                    ) {
                                        selectedCellViewModel
                                            .selectedCell(
                                                index = stateFromViewModel.list.indexOf(
                                                    itemModelSudoku
                                                ),
                                                selectedRow = row,
                                                selectedColum = colum,
                                                isSelected = true,
                                            )
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                val numForCell =
                                    getTestForCell(itemModelSudoku = itemModelSudoku)
                                Text(
                                    text = numForCell,
                                    color = getColorTextForCell(
                                        selectedCell,
                                        index,
                                        row,
                                        colum,
                                    )
                                )
                                index += 1
                            }
                        }
                    }
                }
            }
        }
    }
}