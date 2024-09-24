package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.sudokuTaple

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

/**  OTHER FUN __________________________________________________________________________________*/
fun getTestForCell(itemModelSudoku: ModelSudoku): String {
    return itemModelSudoku.let {
        if (it.isStartedCell)
            it.numInCell.toString()
        else
            if (it.numFromSelectedCell < 1) ""
            else it.numFromSelectedCell.toString()
    }
}


@Composable
fun getColorBackgroundGrandGrid(
    modelSudoku: ModelSudoku?,
    row: Int,
    colum: Int,
): Color {
    val columnGrid =
        if (modelSudoku != null) (modelSudoku.selectedCellIndex / 9) / 3 + 1 else 0
    val rowGrid =
        if (modelSudoku != null) (modelSudoku.selectedCellIndex % 9) / 3 + 1 else 0

    return if ((row == rowGrid && colum == columnGrid))
        MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
    else Color.Unspecified
}

@Composable
fun getColorBoxBackground(
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
        row == modelSudoku?.selectedRow -> selectedCell.copy(alpha = 0.4f)
        colum == modelSudoku?.selectedCol -> selectedCell.copy(alpha = 0.4f)
        itemModelSudoku.isStartedCell -> backgroundColorCell.copy(alpha = 0.1f)
        else -> Color.Unspecified
    }
}

@Composable
fun getColorTextForCell(
    modelSudoku: ModelSudoku?,
    index: Int,
    row: Int,
    colum: Int
): Color {
    val colorTextOnSelectedLine = MaterialTheme.colorScheme.onPrimary
    return when {
        index == modelSudoku?.selectedCellIndex -> colorTextOnSelectedLine
        row == modelSudoku?.selectedRow -> colorTextOnSelectedLine
        colum == modelSudoku?.selectedCol -> colorTextOnSelectedLine
        else -> Color.Unspecified
    }
}
