package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import android.view.Display.Mode
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class IsHowAlmostAnswerUseCase(private val repositorySudokuGame: RepositorySudokuGame) {
    operator fun invoke(isHow: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return repositorySudokuGame.isShowAlmostAnswer(isHow = isHow, modelSudoku = modelSudoku)
    }
}