package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.models.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class SetValueInCellUseCase(private val repositorySudokuGame: RepositorySudokuGame) {
    operator fun invoke(value: Int, modelSudoku: ModelSudoku): ModelSudoku {
        return repositorySudokuGame.setValueInCell(value = value, modelSudoku = modelSudoku)
    }
}