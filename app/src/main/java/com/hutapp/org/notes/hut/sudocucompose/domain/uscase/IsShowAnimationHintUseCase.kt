package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.models.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class IsShowAnimationHintUseCase(private val repositorySudokuGame: RepositorySudokuGame) {
    operator fun invoke(isShowAnimationHint: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return repositorySudokuGame.onOffAnimationHint(
            isShowAnimationHint = isShowAnimationHint,
            modelSudoku = modelSudoku
        )
    }
}