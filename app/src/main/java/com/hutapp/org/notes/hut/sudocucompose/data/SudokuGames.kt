package com.hutapp.org.notes.hut.sudocucompose.data

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

class SudokuGames {
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
                    itemCell.isSelected -> itemCell.copy(isSelected = false)
                    itemCell.selectedCellIndex == index -> {
                        val newItem = itemCell.copy(
                            selectedCol = selectedColum,
                            selectedRow = selectedRow,
                            isSelected = isSelected
                        )
                        selectedItem = newItem
                        newItem
                    }

                    else -> itemCell
                }
            }
        return modelSudoku.copy(listItemCell = newListCells, selectedCell = selectedItem)
    }

    /**Set value in cell ________________________________________________________________________*/
    fun setValueInCell(value: Int, modelSudoku: ModelSudoku): ModelSudoku {
        //todo need fun to check  is correct answer
        val newList = modelSudoku.listItemCell.map { itemCell ->
            if (itemCell.isSelected) {
                itemCell.copy(numFromSelectedCell = value)
            } else
                itemCell
        }
        return modelSudoku.copy(listItemCell = newList)
    }

    /**generate list<Int>________________________________________________________________________ */
    fun getListModelSudoku(level: Int = 1): ModelSudoku {
        // map list<Int> to List<ModelSudoku>
        val listItemCell = generateSudoku().mapIndexed { index, value ->
            val isStartedCell = ((0..level).random()) > 0
            ItemCell(
                numInCell = value,
                isStartedCell = isStartedCell,
                selectedCellIndex = index
            )
        }
        return ModelSudoku(listItemCell = listItemCell)
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