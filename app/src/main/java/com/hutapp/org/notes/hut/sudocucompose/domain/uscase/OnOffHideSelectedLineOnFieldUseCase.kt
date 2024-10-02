package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import androidx.compose.runtime.internal.isLiveLiteralsEnabled
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class OnOffHideSelectedLineOnFieldUseCase(private val repositorySudokuGame: RepositorySudokuGame) {
    operator fun invoke(isHide: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return repositorySudokuGame.onOffHideSelectedLineOnField(
            isHide = isHide,
            modelSudoku = modelSudoku
        )
    }
}