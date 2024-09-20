package com.hutapp.org.notes.hut.sudocucompose.data.repository

import com.hutapp.org.notes.hut.sudocucompose.data.SudokuGames
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositoryModelSudoku

class RepositoryListModelSudokuImpl(private val sudokuGames: SudokuGames) : RepositoryModelSudoku {

    override fun getListForStated(): List<ModelSudoku> {
        return sudokuGames.getListModelSudoku()
    }

}