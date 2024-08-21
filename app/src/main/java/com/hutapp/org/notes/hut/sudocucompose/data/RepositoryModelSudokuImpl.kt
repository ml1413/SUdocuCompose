package com.hutapp.org.notes.hut.sudocucompose.data

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositoryModelSudoku

class RepositoryModelSudokuImpl : RepositoryModelSudoku {
    override fun getModelSudoku(index: Int, selectedRow: Int, selectedColum: Int): ModelSudoku {
        return ModelSudoku(
            selectedCell = index,
            selectedRow = selectedRow,
            selectedCol = selectedColum
        )
    }
}