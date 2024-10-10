package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.models.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class CheckAllAnswerUseCase(private val repositorySudokuGame: RepositorySudokuGame) {
    operator fun invoke(modelSudoku: ModelSudoku): ModelSudoku {
        return repositorySudokuGame.checkAllAnswer(modelSudoku = modelSudoku)
    }
}