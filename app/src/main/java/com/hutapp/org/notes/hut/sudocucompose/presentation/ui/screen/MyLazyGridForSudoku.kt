package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.presentation.SelectedCellViewModel
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.theme.SUdocuComposeTheme


@Composable
fun MyLazyGridForSudoku(
    modifier: Modifier = Modifier,
    selectedCellViewModel: SelectedCellViewModel
) {
    Log.d("TAG1", "MyLazyGrid: ")
    Column() {
        SudokuTableGrid(
            modifier = modifier,
            selectedCellViewModel = selectedCellViewModel
        )
        MyBottomKeyBoard(
            modifier = modifier,
            selectedCellViewModel = selectedCellViewModel
        )
    }
}


@Composable
private fun SudokuTableGrid(
    modifier: Modifier,
    selectedCellViewModel: SelectedCellViewModel
) {
    val listModelSudoku = selectedCellViewModel.selectedCell.observeAsState(emptyList())
    var index = 0
    val colorGrid = MaterialTheme.colorScheme.onBackground
    val selectedCell = listModelSudoku.value.firstOrNull { it.isSelected }
    Card(
        modifier = modifier.padding(start = 16.dp, 16.dp, 16.dp, 0.dp),
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
                                        color = getColorBackground(
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
                            val itemModelSudoku = listModelSudoku.value.get(index)
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
                                    .border(width = 0.1.dp, color = colorGrid)
                                    .clickable(
                                        enabled = itemModelSudoku.isStartedCell.not()
                                    ) {
                                        selectedCellViewModel.selectedCell(
                                            index = listModelSudoku.value.indexOf(itemModelSudoku),
                                            selectedRow = row,
                                            selectedColum = colum,
                                            isSelected = true,
                                        )
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                val numForCell = getTestForCell(itemModelSudoku = itemModelSudoku)
                                Text(
                                    text = numForCell,
                                    color = getColorTextSelectedCell(index, selectedCell)
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


@Composable
private fun MyBottomKeyBoard(
    modifier: Modifier,
    selectedCellViewModel: SelectedCellViewModel
) {
    val listNumber = (1..9).toList()
    LazyVerticalGrid(
        contentPadding = PaddingValues(8.dp, 0.dp, 8.dp, 16.dp),
        columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(listNumber) { value ->
            FloatingActionButton(
                modifier = modifier.padding(8.dp),
                onClick = {
                    selectedCellViewModel.updateCell(value = value)
                }
            ) {
                Text(text = "$value")
            }
        }
    }
}

/**  OTHER FUN __________________________________________________________________________________*/
private fun getTestForCell(itemModelSudoku: ModelSudoku): String {
    return itemModelSudoku.let {
        if (it.isStartedCell)
            it.numInCell.toString()
        else
            if (it.numFromSelectedCell < 1) ""
            else it.numFromSelectedCell.toString()
    }
}

@Composable
private fun getColorTextSelectedCell(
    index: Int,
    modelSudoku: ModelSudoku?
) =
    if (index == modelSudoku?.selectedCellIndex) MaterialTheme.colorScheme.onPrimary else Color.Unspecified

@Composable
private fun getColorBackground(
    modelSudoku: ModelSudoku?,
    row: Int,
    colum: Int,
): Color {
    val columnGrid =
        if (modelSudoku != null) (modelSudoku.selectedCellIndex / 9) / 3 + 1 else 0
    val rowGrid =
        if (modelSudoku != null) (modelSudoku.selectedCellIndex % 9) / 3 + 1 else 0

    return if ((row == rowGrid && colum == columnGrid))
        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
    else Color.Unspecified
}

@Composable
private fun getColorBoxBackground(
    itemModelSudoku: ModelSudoku,
    modelSudoku: ModelSudoku?,
    index: Int,
    row: Int,
    colum: Int
): Color {
    val backgroundColorCell =
        MaterialTheme.colorScheme.onBackground // vertical horizontal cell
    val selectedCell = MaterialTheme.colorScheme.primary // selected cell
    return when {
        index == modelSudoku?.selectedCellIndex -> selectedCell
        row == modelSudoku?.selectedRow -> selectedCell.copy(alpha = 0.2f)
        colum == modelSudoku?.selectedCol -> selectedCell.copy(alpha = 0.2f)
        itemModelSudoku.isStartedCell -> backgroundColorCell.copy(alpha = 0.1f)
        else -> Color.Unspecified
    }
}

//Preview__________________________________________________________________________________________
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    SUdocuComposeTheme(darkTheme = true) {
        MyLazyGridForSudoku(
            selectedCellViewModel = viewModel()
        )
    }
}