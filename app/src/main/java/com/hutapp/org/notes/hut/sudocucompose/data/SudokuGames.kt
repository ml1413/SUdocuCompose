package com.hutapp.org.notes.hut.sudocucompose.data

import android.util.Log
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ColorEnum
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

class SudokuGames {
    /**check all answer __________________________________________________________________________*/
    fun checkAllAnswer(modelSudoku: ModelSudoku): ModelSudoku {
        // check if all answer(setValue) == correctAnswer(startedValue)  =  true
        val allAnswerIssCorrections = modelSudoku
            .listItemCell.map { it.setValue == it.startedValue }
            .contains(false).not()
        //_________________________________________________________________________________________
        return if (allAnswerIssCorrections)
            modelSudoku.copy(isVictory = allAnswerIssCorrections)
        else modelSudoku
    }

    /** selected cell fun_________________________________________________________________________*/
    fun selectedCell(
        modelSudoku: ModelSudoku,
        index: Int,
        selectedRow: Int,
        selectedColum: Int,
        isSelected: Boolean
    ): ModelSudoku {

        var selectedItem: ItemCell? = null

        val newListCells = modelSudoku.listItemCell
            .map { itemCell ->
                when {
                    itemCell.isSelected -> itemCell.copy(
                        isSelected = false,
                        color = ColorEnum.UNSELECTED
                    )

                    itemCell.isStartedCell -> {
                        itemCell.copy(color = ColorEnum.COLOR_STARTED_CELL)
                    }

                    !itemCell.isStartedCell -> {
                        itemCell.copy(color = ColorEnum.UNSELECTED)
                    }

                    else -> itemCell
                }
            }.map { itemCell ->
                when {
                    itemCell.selectedCellIndex == index -> {
                        val newItem = itemCell.copy(
                            color = ColorEnum.SELECTED_CELL,
                            selectedCol = selectedColum,
                            selectedRow = selectedRow,
                            isSelected = isSelected
                        )
                        selectedItem = newItem

                        newItem
                    }

                    itemCell.selectedRow == selectedRow -> {
                        itemCell.copy(color = ColorEnum.SELECT_LINE)
                    }

                    itemCell.selectedCol == selectedColum -> {
                        itemCell.copy(color = ColorEnum.SELECT_LINE)
                    }

                    else -> itemCell
                }
            }
        return modelSudoku.copy(listItemCell = newListCells, selectedCell = selectedItem)
    }

    /**Set value in cell ________________________________________________________________________*/
    fun setValueInCell(value: Int, modelSudoku: ModelSudoku): ModelSudoku {
        val newList = modelSudoku.listItemCell.map { itemCell ->
            if (itemCell.isSelected) {
                Log.d(
                    "TAG1",
                    "setValueInCell: set ${value} ref ${itemCell.startedValue}"
                )
                itemCell.copy(setValue = value)
            } else
                itemCell
        }
        return modelSudoku.copy(listItemCell = newList)
    }

    /**generate list<Int>________________________________________________________________________ */
    fun getListModelSudoku(level: Int = 1): ModelSudoku {
        // map list<Int> to List<ModelSudoku>
        val genList = generateSudoku()
        val newList = mutableListOf<ItemCell>()
        var ind = 0
        for (colum in 1..9) {
            for (row in 1..9) {
                val isStartedCell = ((0..level).random()) > 0
                val value = genList[ind]
                val itemCell = ItemCell(
                    startedValue = value,
                    setValue = if (isStartedCell) value else -1,
                    isStartedCell = isStartedCell,
                    selectedRow = row,
                    selectedCol = colum,
                    selectedCellIndex = ind,
                    color = if (isStartedCell) ColorEnum.COLOR_STARTED_CELL else ColorEnum.UNSELECTED
                )
                newList.add(itemCell)
                ind++
            }
        }

        return ModelSudoku(listItemCell = newList)
    }


    private fun generateSudoku(): List<Int> {
        val grid = Array(9) { IntArray(9) }
        fillGrid(grid)
        return grid.flatMap { it.toList() }
    }

    private fun fillGrid(grid: Array<IntArray>): Boolean {
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (grid[i][j] == 0) {
                    val numbers = (1..9).shuffled()
                    for (num in numbers) {
                        if (isSafe(grid, i, j, num)) {
                            grid[i][j] = num
                            if (fillGrid(grid)) {
                                return true
                            }
                            grid[i][j] = 0
                        }
                    }
                    return false
                }
            }
        }
        return true
    }

    private fun isSafe(grid: Array<IntArray>, row: Int, col: Int, num: Int): Boolean {
        for (i in 0 until 9) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false
            }
        }

        val boxRowStart = row - row % 3
        val boxColStart = col - col % 3
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (grid[boxRowStart + i][boxColStart + j] == num) {
                    return false
                }
            }
        }
        return true
    }
}