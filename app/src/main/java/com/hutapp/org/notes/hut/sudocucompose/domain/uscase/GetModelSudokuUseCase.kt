package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositoryModelSudoku

class GetModelSudokuUseCase(private val repositoryModelSudoku: RepositoryModelSudoku) {
    operator fun invoke(index: Int, selectedRow: Int, selectedColum: Int): ModelSudoku {
        return repositoryModelSudoku.getModelSudoku(
            index = index,
            selectedRow = selectedRow,
            selectedColum = selectedColum
        )
    }
}