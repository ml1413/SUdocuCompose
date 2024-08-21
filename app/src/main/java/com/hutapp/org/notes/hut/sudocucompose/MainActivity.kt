package com.hutapp.org.notes.hut.sudocucompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.ui.theme.SUdocuComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedCellViewModel = ViewModelProvider(this).get(SelectedCellViewModel::class)
        setContent {
            SUdocuComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyLazyGrid(selectedCellViewModel = selectedCellViewModel)
                }
            }
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun MyLazyGrid(
    modifier: Modifier = Modifier,
    selectedCellViewModel: SelectedCellViewModel
) {
    val selectedSellValue = selectedCellViewModel.selectedCell.observeAsState()
    val list = (1..81).map { it }// todo delete temp list
    var index = 0
    val colorGrid = MaterialTheme.colorScheme.onBackground
    Box(
        modifier = modifier.padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
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
                                        selectedSellValue.value,
                                        row,
                                        colum,
                                    )
                                )
                        )
                    }
                }
            }
        }
        //__________________________________________________________________________________________
        Column(modifier = modifier.aspectRatio(1f)) {
            for (colum in 1..9) {
                Row(modifier = modifier.weight(1f)) {
                    for (row in 1..9) {
                        val valueForText = list[index]
                        Box(
                            modifier = modifier
                                .weight(1f)
                                .fillMaxSize()
                                .background(
                                    getColorBoxBackgroung(
                                        selectedSellValue.value,
                                        index,
                                        row,
                                        colum,
                                    )
                                )
                                .border(width = 0.1.dp, color = colorGrid)
                                .clickable {
                                    selectedCellViewModel.selectedCell(
                                        index = list.indexOf(valueForText),
                                        row = row,
                                        colum = colum
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = valueForText.toString(),
                                color = getColorTextSelectedCell(index, selectedSellValue.value)
                            )
                            index += 1
                        }
                    }
                }
            }
        }
        //__________________________________________________________________________________________


    }

}

@Composable
private fun getColorTextSelectedCell(
    index: Int,
    modelSudoku: ModelSudoku?,

    ) =
    if (index == modelSudoku?.selectedCell) MaterialTheme.colorScheme.onPrimary else Color.Unspecified

@Composable
private fun getColorBackground(
    modelSudoku: ModelSudoku?,
    row: Int,
    colum: Int,
): Color {
    val columnGrid =
        if (modelSudoku != null) (modelSudoku.selectedCell / 9) / 3 + 1 else 0
    val rowGrid =
        if (modelSudoku != null) (modelSudoku.selectedCell % 9) / 3 + 1 else 0

    return if (row == rowGrid && colum == columnGrid) MaterialTheme.colorScheme.onBackground.copy(
        alpha = 0.1f
    ) else Color.Unspecified
}

@Composable
private fun getColorBoxBackgroung(
    modelSudoku: ModelSudoku?,
    index: Int,
    row: Int,
    colum: Int
) = if (index == modelSudoku?.selectedCell) {
    MaterialTheme.colorScheme.primary // selected cell
} else if (row == modelSudoku?.selectedRow || colum == modelSudoku?.selectedCol) {
    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f) // vertical horizontal cell
} else {
    Color.Unspecified
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    SUdocuComposeTheme {
        MyLazyGrid(
            selectedCellViewModel = viewModel()
        )
    }
}