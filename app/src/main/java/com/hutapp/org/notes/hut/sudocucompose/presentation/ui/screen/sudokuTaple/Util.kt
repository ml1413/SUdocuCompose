package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.sudokuTaple

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ItemCell

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
    itemItemCell: ItemCell,
    itemCell: ItemCell?,
    index: Int,
    row: Int,
    colum: Int
): Color {
    val backgroundColorCell =
        MaterialTheme.colorScheme.onBackground // vertical horizontal cell
    val selectedCell = MaterialTheme.colorScheme.primary // selected cell
    return when {
        index == itemCell?.selectedCellIndex -> selectedCell
        row == itemCell?.selectedRow -> selectedCell.copy(alpha = 0.4f)
        colum == itemCell?.selectedCol -> selectedCell.copy(alpha = 0.4f)
        itemItemCell.isStartedCell -> backgroundColorCell.copy(alpha = 0.1f)
        else -> Color.Unspecified
    }
}

@Composable
fun getColorTextForCell(
    itemCell: ItemCell?,
    index: Int,
    row: Int,
    colum: Int
): Color {
    val colorTextOnSelectedLine = MaterialTheme.colorScheme.onPrimary
    return when {
        index == itemCell?.selectedCellIndex -> colorTextOnSelectedLine
        row == itemCell?.selectedRow -> colorTextOnSelectedLine
        colum == itemCell?.selectedCol -> colorTextOnSelectedLine
        else -> Color.Unspecified
    }
}
