package com.hutapp.org.notes.hut.sudocucompose.domain.repository

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

interface RepositorySudokuGame {
    fun getListForStated(): List<ModelSudoku>
    fun setValueInCell(value: Int, list: List<ModelSudoku>?): List<ModelSudoku>
}