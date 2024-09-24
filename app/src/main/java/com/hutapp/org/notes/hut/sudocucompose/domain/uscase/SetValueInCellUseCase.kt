package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.data.repository.RepositorySudokuGameImpl
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

class SetValueInCellUseCase(private val repositorySudokuGameImpl: RepositorySudokuGameImpl) {
    operator fun invoke(value: Int, modelSudoku: ModelSudoku): ModelSudoku {
        return repositorySudokuGameImpl.setValueInCell(value = value, modelSudoku = modelSudoku)
    }
}