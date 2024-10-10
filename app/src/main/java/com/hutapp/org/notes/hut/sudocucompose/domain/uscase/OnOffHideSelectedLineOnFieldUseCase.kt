package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.models.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class OnOffHideSelectedLineOnFieldUseCase(private val repositorySudokuGame: RepositorySudokuGame) {
    operator fun invoke(isHide: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return repositorySudokuGame.onOffHideSelectedLineOnField(
            isHide = isHide,
            modelSudoku = modelSudoku
        )
    }
}