package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ColorCellEnum
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.TextStyleEnum
import com.hutapp.org.notes.hut.sudocucompose.presentation.CellViewModel

// sudoku table ________________________________________________________________________________
@Composable
fun SudokuTableGrid(
    modifier: Modifier,
    stateFromViewModel: CellViewModel.GameState.ResumeGame,
    colorGrid: Color,
    onCellClickListener: (index: Int, selectedRow: Int, selectedColum: Int, isSelected: Boolean) -> Unit,
) {
    var index = 0
    Card(
        border = BorderStroke(1.dp, colorGrid),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)

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
                                        color = colorGrid,
                                    )
                                    .aspectRatio(1f)
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
                            val itemModelSudoku =
                                stateFromViewModel.modelSudoku.listItemCell.get(index)
                            Box(
                                modifier = modifier
                                    .weight(1f)
                                    .fillMaxSize()
                                    .background(
                                        with(MaterialTheme.colorScheme) {
                                            when (itemModelSudoku.colorCell) {
                                                ColorCellEnum.UNSELECTED ->
                                                    Color.Unspecified

                                                ColorCellEnum.SELECTED_CELL ->
                                                    primary

                                                ColorCellEnum.SELECT_LINE ->
                                                    primary.copy(alpha = 0.4f)

                                                ColorCellEnum.COLOR_STARTED_CELL ->
                                                    onBackground.copy(alpha = 0.1f)

                                                ColorCellEnum.SELECTED_BLOCK ->
                                                    primary.copy(alpha = 0.4f)
                                            }
                                        }

                                    )
                                    .border(
                                        width = 0.1.dp,
                                        color = colorGrid,
                                    )
                                    .clickable(
                                        enabled = itemModelSudoku.isStartedCell.not()
                                    ) {
                                        val indexCell = stateFromViewModel
                                            .modelSudoku
                                            .listItemCell
                                            .indexOf(itemModelSudoku)

                                        onCellClickListener(
                                            indexCell,
                                            row,
                                            colum,
                                            true
                                        )
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                val numForCell =
                                    getTestForCell(itemItemCell = itemModelSudoku)
                                Box() {


                                    when (itemModelSudoku.textStyle) {
                                        TextStyleEnum.UNSELECTED -> {
                                            Text(text = numForCell)
                                        }

                                        TextStyleEnum.ON_STARTED_CELL -> {
                                            Text(
                                                text = numForCell,
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        }

                                        TextStyleEnum.SELECTED_IN_CELL -> {
                                            Text(
                                                text = numForCell,
                                                color = MaterialTheme.colorScheme.onPrimary
                                            )
                                        }

                                        TextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_NO_STARTED -> {
                                            Text(
                                                text = numForCell,
                                                color = MaterialTheme.colorScheme.onPrimary
                                            )
                                        }


                                        TextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_STARTED -> {
                                            Text(
                                                text = numForCell,
                                                color = MaterialTheme.colorScheme.onPrimary,
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        }

                                        TextStyleEnum.ERROR -> {
                                            Text(
                                                text = numForCell,
                                                color = Color.Red
                                            )
                                        }
                                    }
                                }
                                index += 1
                            }
                        }
                    }
                }
            }
        }
    }
}

