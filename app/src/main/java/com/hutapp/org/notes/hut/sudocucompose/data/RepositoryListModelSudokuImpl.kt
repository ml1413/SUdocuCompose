package com.hutapp.org.notes.hut.sudocucompose.data

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositoryModelSudoku

class RepositoryListModelSudokuImpl : RepositoryModelSudoku {
    //todo need generated list for sudoku table
    private val listCells = List(9 * 9) {
        ModelSudoku(
            selectedCellIndex = it,
            intValue = it + 1
        )
    }

    override fun getListForStated(): List<ModelSudoku> {
        return listCells
    }

}