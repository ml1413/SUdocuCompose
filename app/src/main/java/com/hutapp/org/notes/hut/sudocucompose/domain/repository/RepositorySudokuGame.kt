package com.hutapp.org.notes.hut.sudocucompose.domain.repository

import com.hutapp.org.notes.hut.sudocucompose.domain.models.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.models.ModelSudoku

interface RepositorySudokuGame {
    suspend fun getListForStated(): ModelSudoku
    fun setValueInCell(value: Int, modelSudoku: ModelSudoku): ModelSudoku
    fun checkAllAnswer(modelSudoku: ModelSudoku): ModelSudoku
    fun unselectedCell(modelSudoku: ModelSudoku): ModelSudoku
    fun selectedCell(
        modelSudoku: ModelSudoku,
        itemCell: ItemCell
    ): ModelSudoku

    fun onOffHideSelectedLineOnField(isHide: Boolean, modelSudoku: ModelSudoku): ModelSudoku
    fun isShowErrorAnswer(isShowError: Boolean, modelSudoku: ModelSudoku): ModelSudoku
    fun isShowAlmostAnswer(isHow: Boolean, modelSudoku: ModelSudoku): ModelSudoku
    fun isShowCorrectAnswer(isShow: Boolean, modelSudoku: ModelSudoku): ModelSudoku
    fun onOffAnimationHint(isShowAnimationHint: Boolean, modelSudoku: ModelSudoku): ModelSudoku
    suspend fun saveInRoom(modelSudoku: ModelSudoku)
}