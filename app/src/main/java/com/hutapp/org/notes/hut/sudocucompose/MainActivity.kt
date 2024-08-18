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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hutapp.org.notes.hut.sudocucompose.ui.theme.SUdocuComposeTheme

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
                    Greeting()
                }
            }
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val list = (1..81).map { it }
    var index = 0
    val itemSelectedIndex = remember { mutableStateOf(-1) }
    val rowSelected = remember { mutableStateOf(-1) }
    val columSelected = remember { mutableStateOf(-1) }
    val colorGrid = MaterialTheme.colorScheme.onBackground
    val columnGrid =
        if (itemSelectedIndex.value > 0) (itemSelectedIndex.value / 9) / 3 + 1 else 0
    val rowGrid =
        if (itemSelectedIndex.value > 0) (itemSelectedIndex.value % 9) / 3 + 1 else 0
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
                                    color = getColorBackground(row, rowGrid, colum, columnGrid)
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
                                    getColor(
                                        index,
                                        itemSelectedIndex,
                                        row,
                                        rowSelected,
                                        colum,
                                        columSelected
                                    )
                                )
                                .border(width = 0.1.dp, color = colorGrid)
                                .clickable {
                                    rowSelected.value = row
                                    columSelected.value = colum
                                    itemSelectedIndex.value = list.indexOf(valueForText)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = valueForText.toString(),
                                color = getColorTextSelectedCell(index, itemSelectedIndex)
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
    itemSelectedIndex: MutableState<Int>
) =
    if (index == itemSelectedIndex.value) MaterialTheme.colorScheme.onPrimary else Color.Unspecified

@Composable
private fun getColorBackground(
    row: Int,
    rowGrid: Int,
    colum: Int,
    columnGrid: Int
) = if (row == rowGrid && colum == columnGrid) MaterialTheme.colorScheme.onBackground.copy(
    alpha = 0.1f
) else Color.Unspecified

@Composable
private fun getColor(
    index: Int,
    itemSelectedIndex: MutableState<Int>,
    row: Int,
    rowSelected: MutableState<Int>,
    colum: Int,
    columSelected: MutableState<Int>
) = if (index == itemSelectedIndex.value) {
    MaterialTheme.colorScheme.primary // selected cell
} else if (row == rowSelected.value || colum == columSelected.value) {
    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f) // vertical horizontal cell
} else {
    Color.Unspecified
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    SUdocuComposeTheme {
        Greeting()
    }
}