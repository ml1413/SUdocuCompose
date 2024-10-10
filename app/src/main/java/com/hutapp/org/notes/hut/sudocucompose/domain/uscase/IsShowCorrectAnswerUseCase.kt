package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.models.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class IsShowCorrectAnswerUseCase(private val repositorySudokuGame: RepositorySudokuGame) {
    operator fun invoke(isShow: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return repositorySudokuGame.isShowCorrectAnswer(isShow = isShow, modelSudoku = modelSudoku)
    }
}