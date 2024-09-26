package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

/**  OTHER FUN __________________________________________________________________________________*/
fun getTestForCell(itemItemCell: ItemCell): String {
    return itemItemCell.let {
        if (it.isStartedCell)
            it.startedValue.toString()
        else
            if (it.setValue < 1) ""
            else it.setValue.toString()
    }
}


@Composable
fun getColorBackgroundGrandGrid(
    itemCell: ItemCell?,
    row: Int,
    colum: Int,
): Color {
    val columnGrid =
        if (itemCell != null) (itemCell.selectedCellIndex / 9) / 3 + 1 else 0
    val rowGrid =
        if (itemCell != null) (itemCell.selectedCellIndex % 9) / 3 + 1 else 0

    return if ((row == rowGrid && colum == columnGrid))
        MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
    else Color.Unspecified
}

@Composable
fun getColorBoxBackground(
    modelSudoku: ModelSudoku,
    index: Int,
    row: Int,
    colum: Int
): Color {
    val backgroundColorCell =
        MaterialTheme.colorScheme.onBackground // vertical horizontal cell
    val colorSelectedCell = MaterialTheme.colorScheme.primary // selected cell
    return when {
        index == modelSudoku.selectedCell?.selectedCellIndex -> colorSelectedCell
        row == modelSudoku.selectedCell?.selectedRow -> colorSelectedCell.copy(alpha = 0.4f)
        colum == modelSudoku.selectedCell?.selectedCol -> colorSelectedCell.copy(alpha = 0.4f)
        modelSudoku.listItemCell[index].isStartedCell -> backgroundColorCell.copy(alpha = 0.1f)
        else -> Color.Unspecified
    }
}

@Composable
fun getColorTextForCell(
    modelSudoku: ModelSudoku,
    index: Int,
    row: Int,
    colum: Int
): Color {
    val colorTextOnSelectedLine = MaterialTheme.colorScheme.onPrimary
    val errorColor = Color.Red// todo need delete
    return when {
        modelSudoku.listItemCell[index].startedValue != modelSudoku.listItemCell[index].setValue -> errorColor
        index == modelSudoku.selectedCell?.selectedCellIndex -> colorTextOnSelectedLine
        row == modelSudoku.selectedCell?.selectedRow -> colorTextOnSelectedLine
        colum == modelSudoku.selectedCell?.selectedCol -> colorTextOnSelectedLine
        else -> Color.Unspecified
    }
}
