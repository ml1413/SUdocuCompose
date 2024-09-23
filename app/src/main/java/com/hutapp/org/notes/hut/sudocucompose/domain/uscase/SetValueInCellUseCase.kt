package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.data.repository.RepositorySudokuGameImpl
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

class SetValueInCellUseCase(private val repositorySudokuGameImpl: RepositorySudokuGameImpl) {
    operator fun invoke(value: Int, list: List<ModelSudoku>?): List<ModelSudoku> {
        return repositorySudokuGameImpl.setValueInCell(value = value, list = list)
    }
}