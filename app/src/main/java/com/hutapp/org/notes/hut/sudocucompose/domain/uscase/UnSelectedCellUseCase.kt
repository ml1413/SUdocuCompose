package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class UnSelectedCellUseCase(private val repositorySudokuGame: RepositorySudokuGame) {
    operator fun invoke(modelSudoku: ModelSudoku): ModelSudoku {
       return repositorySudokuGame.unselectedCell(modelSudoku = modelSudoku)
    }
}