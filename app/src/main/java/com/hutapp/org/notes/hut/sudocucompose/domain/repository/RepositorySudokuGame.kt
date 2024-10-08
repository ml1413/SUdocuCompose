package com.hutapp.org.notes.hut.sudocucompose.domain.repository

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

interface RepositorySudokuGame {
    fun getListForStated(): ModelSudoku
    fun setValueInCell(value: Int, modelSudoku: ModelSudoku): ModelSudoku
    fun checkAllAnswer(modelSudoku: ModelSudoku): ModelSudoku
    fun unselectedCell(modelSudoku: ModelSudoku): ModelSudoku
    fun selectedCell(
        modelSudoku: ModelSudoku,
        itemCell :ItemCell
    ): ModelSudoku

    fun onOffHideSelectedLineOnField(isHide: Boolean, modelSudoku: ModelSudoku): ModelSudoku
    fun isShowErrorAnswer(isShowError: Boolean, modelSudoku: ModelSudoku): ModelSudoku
}