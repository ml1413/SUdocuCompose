package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hutapp.org.notes.hut.sudocucompose.domain.models.AlmostHint
import com.hutapp.org.notes.hut.sudocucompose.domain.models.ColorCellEnum
import com.hutapp.org.notes.hut.sudocucompose.domain.models.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.models.TextStyleEnum
import com.hutapp.org.notes.hut.sudocucompose.presentation.CellViewModel
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.theme.colorAlmost
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.theme.colorAlmostDark
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.theme.colorCorrectAnswer
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.theme.colorCorrectAnswerDark
import kotlinx.coroutines.delay

// sudoku table ________________________________________________________________________________
@Composable
fun SudokuTableGrid(
    modifier: Modifier,
    isAnimated: MutableState<Boolean>,
    stateFromViewModel: CellViewModel.GameState.ResumeGame,
    colorGrid: Color,
    onCellClickListener: (ItemCell) -> Unit,
) {

    val colorAlmost =
        if (isSystemInDarkTheme()) colorAlmostDark else colorAlmost
    val colorCorrectAnswer =
        if (isSystemInDarkTheme()) colorCorrectAnswerDark else colorCorrectAnswer

    val almostHintState = almostHintState()

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

                                                ColorCellEnum.STARTED_CELL_ON_LINE ->
                                                    primary.copy(alpha = 0.4f)
                                            }
                                        }

                                    )
                                    .border(
                                        width = 0.1.dp,
                                        color = colorGrid,
                                    )
                                    .padding(2.dp)
                                    .border(
                                        width = 1.dp,
                                        color =
                                        when {
                                            itemModelSudoku.almostHintRow == AlmostHint.ROW
                                                    && almostHintState == AlmostHint.ROW
                                            -> colorAlmost

                                            itemModelSudoku.almostHintColumn == AlmostHint.COLUMN
                                                    && almostHintState == AlmostHint.COLUMN -> colorAlmost

                                            itemModelSudoku.almostHintBlock == AlmostHint.BLOCK
                                                    && almostHintState == AlmostHint.BLOCK -> colorAlmost

                                            else -> Color.Unspecified
                                        }
                                    )
                                    .clickable(
                                        enabled = itemModelSudoku.isStartedCell.not()
                                    ) {
                                        val item = itemModelSudoku.copy(
                                            isSelected = true
                                        )
                                        onCellClickListener(item)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                val numForCell =
                                    getTestForCell(itemItemCell = itemModelSudoku)
                                Box(contentAlignment = Alignment.Center) {
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
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        }

                                        TextStyleEnum.ERROR -> {
                                            Text(
                                                text = numForCell,
                                                color = Color.Red
                                            )
                                        }

                                        TextStyleEnum.ALMOST ->
                                            Text(
                                                text = numForCell,
                                                color = colorAlmost
                                            )

                                        TextStyleEnum.ALL_IS_CORRECT -> {
                                            Text(
                                                text = numForCell,
                                                color = colorCorrectAnswer
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

@Composable
private fun almostHintState(): AlmostHint {
    val almostHintState = remember { mutableStateOf(AlmostHint.INITIAL) }.apply {
        LaunchedEffect(null) {
            listOf(
                AlmostHint.INITIAL,
                AlmostHint.ROW,
                AlmostHint.INITIAL,
                AlmostHint.COLUMN,
                AlmostHint.INITIAL,
                AlmostHint.BLOCK
            ).apply {
                repeat(1000) {
                    forEach { almostHint ->
                        delay(3000)
                        value = almostHint
                    }
                }
            }

        }
    }.value
    return almostHintState
}

