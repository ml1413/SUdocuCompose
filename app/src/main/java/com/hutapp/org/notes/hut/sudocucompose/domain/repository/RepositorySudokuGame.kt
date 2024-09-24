package com.hutapp.org.notes.hut.sudocucompose.domain.repository

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

interface RepositorySudokuGame {
    fun getListForStated(): ModelSudoku
    fun setValueInCell(value: Int, modelSudoku: ModelSudoku): ModelSudoku
    fun selectedCell(
        modelSudoku: ModelSudoku,
        index: Int,
        selectedRow: Int,
        selectedColum: Int,
        isSelected: Boolean
    ): ModelSudoku
}