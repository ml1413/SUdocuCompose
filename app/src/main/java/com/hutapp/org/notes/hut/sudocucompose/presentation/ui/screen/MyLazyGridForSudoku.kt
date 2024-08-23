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
        GrandGrid(
            modifier = modifier,
            selectedCellViewModel = selectedCellViewModel
        )
        MyBottomKeyBoard(modifier, selectedCellViewModel)
    }
}


@Composable
private fun GrandGrid(
    modifier: Modifier,
    selectedCellViewModel: SelectedCellViewModel
) {
    val listModelSudoku = selectedCellViewModel.selectedCell.observeAsState()
    var index = 0
    val colorGrid = MaterialTheme.colorScheme.onBackground
    val selectedCell = listModelSudoku.value?.filter { it.isSelected }.let {
        if (it?.isNotEmpty() == true) it.first() else null
    }
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
                            val itemModelSudoku = listModelSudoku.value?.get(index)
                            Box(
                                modifier = modifier
                                    .weight(1f)
                                    .fillMaxSize()
                                    .background(
                                        getColorBoxBackground(
                                            selectedCell,
                                            index,
                                            row,
                                            colum,
                                        )
                                    )
                                    .border(width = 0.1.dp, color = colorGrid)
                                    .clickable {
                                        selectedCellViewModel.selectedCell(
                                            index = listModelSudoku.value?.indexOf(
                                                itemModelSudoku
                                            )
                                                ?: 0,
                                            selectedRow = row,
                                            selectedColum = colum,
                                            isSelected = true
                                        )
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = itemModelSudoku?.intValue.toString(),
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
        items(listNumber) {
            FloatingActionButton(
                modifier = modifier.padding(8.dp),
                onClick = {
                    selectedCellViewModel.updateCell(value = it)
                }) {
                Text(text = "$it")
            }
        }
    }
}

/**  OTHER FUN __________________________________________________________________________________*/
@Composable
private fun getColorTextSelectedCell(
    index: Int,
    modelSudoku: ModelSudoku?,

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

    return if (row == rowGrid && colum == columnGrid) MaterialTheme.colorScheme.onBackground.copy(
        alpha = 0.1f
    ) else Color.Unspecified
}

@Composable
private fun getColorBoxBackground(
    modelSudoku: ModelSudoku?,
    index: Int,
    row: Int,
    colum: Int
) = if (index == modelSudoku?.selectedCellIndex) {
    MaterialTheme.colorScheme.primary // selected cell
} else if (row == modelSudoku?.selectedRow || colum == modelSudoku?.selectedCol) {
    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f) // vertical horizontal cell
} else {
    Color.Unspecified
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