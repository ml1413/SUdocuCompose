package com.hutapp.org.notes.hut.sudocucompose.data

import android.util.Log
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ColorCellEnum
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.TextStyleEnum
import javax.inject.Inject

class SudokuGames @Inject constructor() {
    /** on off show Error answer__________________________________________________________________*/
    fun isShowErrorAnswer(isShowError: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return modelSudoku.copy(isShowErrorAnswer = isShowError)
    }

    /** on off hide selected line_________________________________________________________________*/
    fun onOffHideSelectedLineOnField(isHide: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        Log.d("TAG1", "onOffHideSelectedLineOnField: ")
        return modelSudoku.copy(isHideSelected = isHide)
    }

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

    /** unselected cell___________________________________________________________________________*/

    fun unselectedCell(modelSudoku: ModelSudoku): ModelSudoku {
        val newListCells = getListUnselectedItem(modelSudoku)
        return modelSudoku.copy(listItemCell = newListCells, hasSelectedCells = false)
    }

    /** selected cell fun_________________________________________________________________________*/
    fun selectedCell(
        modelSudoku: ModelSudoku,
        index: Int,
        selectedRow: Int,
        selectedColum: Int,
        isSelected: Boolean
    ): ModelSudoku {
        val listIndexFromBlock = getIndexCellInSelectedBlock(index = index)

        val newListCells = getListUnselectedItem(modelSudoku = modelSudoku)
            .map { itemCell ->
                // text style for text on cell (if error color red)
                val textStyleErrorOrNot =
                    getTextStyleErrorOrNot(itemCell = itemCell, modelSudoku = modelSudoku)

                // set new color on cells
                when {
                    itemCell.selectedCellIndex == index -> {
                        itemCell.copy(
                            selectedCol = selectedColum,
                            selectedRow = selectedRow,
                            isSelected = isSelected,
                            colorCell = ColorCellEnum.SELECTED_CELL,
                            textStyle = TextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_NO_STARTED
                        )
                    }

                    itemCell.selectedCol == selectedColum || itemCell.selectedRow == selectedRow -> {
                        itemCell.copy(
                            colorCell = ColorCellEnum.SELECT_LINE,
                            textStyle = textStyleErrorOrNot
                        )
                    }

                    listIndexFromBlock.contains(itemCell.selectedCellIndex) -> {
                        itemCell.copy(
                            colorCell = ColorCellEnum.SELECTED_BLOCK,
                            textStyle = textStyleErrorOrNot
                        )
                    }
                    //todo possibly rewrites!!!!!!!!!!!!!!!!!!! need debug
//                    itemCell.setValue != itemCell.startedValue -> {
//                        itemCell.copy(textStyle = TextStyleEnum.ERROR)
//                    }

                    else -> itemCell
                }
            }
        return modelSudoku.copy(listItemCell = newListCells, hasSelectedCells = true)
    }


    /**Set value in cell ________________________________________________________________________*/
    fun setValueInCell(value: Int, modelSudoku: ModelSudoku): ModelSudoku {
        val newList = modelSudoku.listItemCell.map { itemCell ->
            if (itemCell.isSelected) {

                itemCell.copy(
                    setValue = value
                )
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
                    colorCell = if (isStartedCell) ColorCellEnum.COLOR_STARTED_CELL else ColorCellEnum.UNSELECTED,
                    textStyle = TextStyleEnum.ON_STARTED_CELL
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


    /** Other fun _________________________________________________________________________________
    _______________________________________________________________________________________________
    _____________________________________________________________________________________________*/
    private fun getTextStyleErrorOrNot(
        itemCell: ItemCell,
        modelSudoku: ModelSudoku
    ): TextStyleEnum {
        val textStyleErrorOrNotFotLineAndBlock =
            if (itemCell.setValue != itemCell.startedValue && modelSudoku.isShowErrorAnswer)
                TextStyleEnum.ERROR
            else
            //text on line vertical horizontal or block if is started text BOLD
                if (itemCell.isStartedCell)
                    TextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_STARTED
                else
                    TextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_NO_STARTED
        //__________________________________________________________________


        return textStyleErrorOrNotFotLineAndBlock
    }

    private fun getStaleTextErrorOrNotForCell(
        itemCell: ItemCell,
        modelSudoku: ModelSudoku
    ): TextStyleEnum {
        val textStyleErrorOrNot =
            if (itemCell.setValue != itemCell.startedValue && modelSudoku.isShowErrorAnswer)
                TextStyleEnum.ERROR
            else
                TextStyleEnum.UNSELECTED
        return textStyleErrorOrNot
    }

    private fun getIndexCellInSelectedBlock(index: Int): List<Int> {
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
        return blockIndices.toList()

    }

    private fun getListUnselectedItem(modelSudoku: ModelSudoku): List<ItemCell> {
        val newListCells = modelSudoku.listItemCell
            .map { itemCell ->
                // text style for text on cell (if error color red)
                val textStyleErrorOrNot = getStaleTextErrorOrNotForCell(itemCell, modelSudoku)
                // clear all selected reset color
                when {
                    itemCell.isSelected -> itemCell.copy(
                        isSelected = false,
                        colorCell = ColorCellEnum.UNSELECTED,
                        textStyle = TextStyleEnum.UNSELECTED
                    )

                    itemCell.isStartedCell -> {
                        itemCell.copy(
                            colorCell = ColorCellEnum.COLOR_STARTED_CELL,
                            textStyle = TextStyleEnum.ON_STARTED_CELL
                        )
                    }

                    !itemCell.isStartedCell -> {
                        itemCell.copy(
                            colorCell = ColorCellEnum.UNSELECTED,
                            textStyle = textStyleErrorOrNot
                        )
                    }

                    else -> itemCell
                }
            }
        return newListCells
    }


}
