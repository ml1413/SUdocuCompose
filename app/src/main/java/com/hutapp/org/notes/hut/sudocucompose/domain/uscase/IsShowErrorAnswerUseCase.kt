package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.models.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class IsShowErrorAnswerUseCase(private val repositorySudokuGame: RepositorySudokuGame) {
    operator fun invoke(isShowError: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return repositorySudokuGame.isShowErrorAnswer(
            isShowError = isShowError,
            modelSudoku = modelSudoku
        )
    }
}