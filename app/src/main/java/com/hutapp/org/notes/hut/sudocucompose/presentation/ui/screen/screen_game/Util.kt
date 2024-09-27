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
private fun getIndexCellInSelectedBlock(index: Int): MutableList<Int> {
    // Ширина и высота сетки судоку
    val gridSize = 9
    val blockSize = 3

    // Вычисляем строку и столбец ячейки
    val rowLine = index / gridSize
    val colLine = index % gridSize

    // Вычисляем левый верхний угол блока
    val blockRowStart = (rowLine / blockSize) * blockSize
    val blockColStart = (colLine / blockSize) * blockSize

    val blockIndices = mutableListOf<Int>()
    for (r in blockRowStart until blockRowStart + blockSize) {
        for (c in blockColStart until blockColStart + blockSize) {
            blockIndices.add(r * gridSize + c)
        }
    }
    return blockIndices
}

@Composable
fun getColorTextForCell(
    modelSudoku: ModelSudoku,
    index: Int,
    row: Int,
    colum: Int
): Color {
    val blockIndices = getIndexCellInSelectedBlock(index)

    val colorTextOnSelectedLine = MaterialTheme.colorScheme.onPrimary
    val errorColor = Color.Red// todo need delete
    return when {
        modelSudoku.listItemCell[index].startedValue != modelSudoku.listItemCell[index].setValue -> errorColor
        index == modelSudoku.selectedCell?.selectedCellIndex -> colorTextOnSelectedLine
        row == modelSudoku.selectedCell?.selectedRow -> colorTextOnSelectedLine
        colum == modelSudoku.selectedCell?.selectedCol -> colorTextOnSelectedLine
        blockIndices.contains(modelSudoku.selectedCell?.selectedCellIndex) -> colorTextOnSelectedLine
        else -> Color.Unspecified
    }
}
