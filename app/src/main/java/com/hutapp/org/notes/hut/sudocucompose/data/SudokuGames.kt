package com.hutapp.org.notes.hut.sudocucompose.data

import android.util.Log
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ColorCellEnum
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.TextStyleEnum
import javax.inject.Inject

// todo нужно сделать отключение жолтого и зеленого
class SudokuGames @Inject constructor() {
    /** on off show Error answer__________________________________________________________________*/
    fun isShowErrorAnswer(isShowError: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return modelSudoku.copy(isShowErrorAnswer = isShowError)
    }

    /**on off almost answer 8 out of 9 ___________________________________________________________*/
    fun isShowAlmostAnswer(isHow: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return modelSudoku.copy(isShowAlmostAnswer = isHow)
    }

    /** on off hide selected line_________________________________________________________________*/
    fun onOffHideSelectedLineOnField(isHide: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        Log.d("TAG1", "onOffHideSelectedLineOnField: ")
        return modelSudoku.copy(isHideSelected = isHide)
    }

    /**on of correct answer 9 out of 9 row column block___________________________________________*/
    fun isShowCorrectAnswer(isShow: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return modelSudoku.copy(isShowCorrectAnswer = isShow)
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
        itemCell: ItemCell
    ): ModelSudoku {
        // get unselected list______________________________________________________________________
        val unselectedList = getListUnselectedItem(modelSudoku = modelSudoku)
        // if selected is the same exit of fun _____________________________________________________
        if (modelSudoku.listItemCell.contains(itemCell))
            return modelSudoku.copy(listItemCell = unselectedList, hasSelectedCells = false)
        //set color and text on cells_______________________________________________________________
        val newList = unselectedList.map { itItem ->
            // text style for text on cell (if error color red)
            val textStyleErrorOrNot =
                getTextStyleErrorOrNotOnLieAndBlock(
                    itemCell = itItem,
                    modelSudoku = modelSudoku
                )

            // set new color on cells
            when {
                itItem.selectedCellIndex == itemCell.selectedCellIndex -> {
                    itItem.copy(
                        isSelected = itemCell.isSelected,
                        colorCell = ColorCellEnum.SELECTED_CELL,
                        textStyle = TextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_NO_STARTED
                    )
                }

                itItem.column == itemCell.column || itItem.row == itemCell.row -> {
                    itItem.copy(
                        colorCell =
                        if (itItem.isStartedCell) ColorCellEnum.STARTED_CELL_ON_LINE else ColorCellEnum.SELECT_LINE,
                        textStyle = textStyleErrorOrNot
                    )
                }

                itItem.block == itemCell.block -> {
                    itItem.copy(
                        colorCell = if (itItem.isStartedCell) ColorCellEnum.STARTED_CELL_ON_LINE else ColorCellEnum.SELECTED_BLOCK,
                        textStyle = textStyleErrorOrNot
                    )
                }

                else -> itItem
            }
        }
        return modelSudoku.copy(listItemCell = newList, hasSelectedCells = true)
    }


    /**Set value in cell ________________________________________________________________________*/
    fun setValueInCell(value: Int, modelSudoku: ModelSudoku): ModelSudoku {
        val newList = modelSudoku.listItemCell.map { itemCell ->
            if (itemCell.isSelected) {
                Log.d("TAG1", "setValueInCell: ${itemCell.isSelected}")
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
                    block = ((row - 1) / 3) * 3 + ((colum - 1) / 3),
                    row = row,
                    column = colum,
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
    private fun getTextStyleErrorOrNotOnLieAndBlock(
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

    private fun getStyleTextErrorOrNotForCell(
        itemCell: ItemCell,
        modelSudoku: ModelSudoku,
        rowColumnBox: Triple<List<Int>, List<Int>, List<Int>>
    ): TextStyleEnum {
        val textStyleErrorOrNot =
            if (itemCell.setValue != itemCell.startedValue && modelSudoku.isShowErrorAnswer && itemCell.setValue > 0)
                TextStyleEnum.ERROR
            else if (
                (rowColumnBox.first[itemCell.column - 1] == 8 && modelSudoku.isShowAlmostAnswer) ||
                (rowColumnBox.second[itemCell.row - 1] == 8 && modelSudoku.isShowAlmostAnswer) ||
                (rowColumnBox.third[itemCell.block] == 8 && modelSudoku.isShowAlmostAnswer)
            )
                TextStyleEnum.ALMOST
            else if (
                (rowColumnBox.first[itemCell.column - 1] == 9 && modelSudoku.isShowCorrectAnswer) ||
                (rowColumnBox.second[itemCell.row - 1] == 9 && modelSudoku.isShowCorrectAnswer) ||
                (rowColumnBox.third[itemCell.block] == 9 && modelSudoku.isShowCorrectAnswer)
            )
                TextStyleEnum.ALL_IS_CORRECT
            else
                TextStyleEnum.UNSELECTED
        return textStyleErrorOrNot
    }


    private fun getListUnselectedItem(modelSudoku: ModelSudoku): List<ItemCell> {
        val rowColumnPair = getRowsAndColumnsCountCorrectAnswer(modelSudoku = modelSudoku)

        val newListCells = modelSudoku.listItemCell
            .map { itemCell ->
                // text style for text on cell (if error color red)
                val textStyleErrorOrAlmostORNot =
                    getStyleTextErrorOrNotForCell(itemCell, modelSudoku, rowColumnPair)
                // clear all selected reset color

                when {
                    itemCell.isSelected -> itemCell.copy(
                        isSelected = false,
                        colorCell = ColorCellEnum.UNSELECTED,
                        textStyle = textStyleErrorOrAlmostORNot,

                        )

                    itemCell.isStartedCell -> {
                        itemCell.copy(
                            colorCell = ColorCellEnum.COLOR_STARTED_CELL,
                            textStyle =
                            if (textStyleErrorOrAlmostORNot != TextStyleEnum.UNSELECTED)
                                textStyleErrorOrAlmostORNot
                            else
                                TextStyleEnum.ON_STARTED_CELL,

                            )
                    }

                    !itemCell.isStartedCell -> {
                        itemCell.copy(
                            colorCell = ColorCellEnum.UNSELECTED,
                            textStyle = textStyleErrorOrAlmostORNot,
                        )
                    }

                    else -> itemCell
                }
            }
        return newListCells
    }


    private fun getRowsAndColumnsCountCorrectAnswer(modelSudoku: ModelSudoku): Triple<List<Int>, List<Int>, List<Int>> {
        val listRows = MutableList(9) { mutableListOf<Int>() }
        val listColumns = MutableList(9) { mutableListOf<Int>() }
        val listBlock = MutableList(9) { mutableListOf<Int>() }

        modelSudoku.listItemCell.forEach { itemCell ->
            if (itemCell.setValue == itemCell.startedValue) {
                listRows[itemCell.column - 1].add(1)
                listColumns[itemCell.row - 1].add(1)
                listBlock[itemCell.block].add(1)
            }

        }
        return Triple(
            listRows.map { it.sum() },
            listColumns.map { it.sum() },
            listBlock.map { it.sum() })

    }


}
