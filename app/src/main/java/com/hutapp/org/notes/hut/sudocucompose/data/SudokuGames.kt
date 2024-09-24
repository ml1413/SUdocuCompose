package com.hutapp.org.notes.hut.sudocucompose.data

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

class SudokuGames {
    /** selected cell fun_________________________________________________________________________*/
     fun selectedCell(
        listModelSudoku: List<ModelSudoku>?,
        index: Int,
        selectedRow: Int,
        selectedColum: Int,
        isSelected: Boolean
    ): List<ModelSudoku> {
        val newListCells = listModelSudoku
            ?.map { modelSudoku ->
                when {
                    modelSudoku.isSelected -> modelSudoku.copy(isSelected = false)
                    modelSudoku.selectedCellIndex == index -> modelSudoku.copy(
                        selectedCol = selectedColum,
                        selectedRow = selectedRow,
                        isSelected = isSelected
                    )

                    else -> modelSudoku
                }
            }
        return newListCells ?: emptyList()
    }
    /**Set value in cell ________________________________________________________________________*/
    fun setValueInCell(value: Int, list: List<ModelSudoku>?): List<ModelSudoku> {
        val newList = list?.map { modelSudoku ->
            if (modelSudoku.isSelected) {
                modelSudoku.copy(numFromSelectedCell = value)
            } else
                modelSudoku
        }
        return newList ?: emptyList()
    }

    /**generate list<Int>________________________________________________________________________ */
    fun getListModelSudoku(level: Int = 1): List<ModelSudoku> {
        // map list<Int> to List<ModelSudoku>
        return generateSudoku().mapIndexed { index, value ->
            val isStartedCell = ((0..level).random()) > 0
            ModelSudoku(
                numInCell = value,
                isStartedCell = isStartedCell,
                selectedCellIndex = index
            )
        }
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