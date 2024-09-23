package com.hutapp.org.notes.hut.sudocucompose.data.repository

import com.hutapp.org.notes.hut.sudocucompose.data.SudokuGames
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class RepositorySudokuGameImpl(private val sudokuGames: SudokuGames) : RepositorySudokuGame {

    override fun getListForStated(): List<ModelSudoku> {
        return sudokuGames.getListModelSudoku()
    }

    override fun setValueInCell(value: Int, list: List<ModelSudoku>?): List<ModelSudoku> {
        return sudokuGames.setValueInCell(value = value, list = list)
    }
}