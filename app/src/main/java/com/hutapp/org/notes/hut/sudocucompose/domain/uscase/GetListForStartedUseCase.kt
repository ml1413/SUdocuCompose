package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class GetListForStartedUseCase(private val repositorySudokuGame: RepositorySudokuGame) {
    operator fun invoke(): List<ModelSudoku> {
        return repositorySudokuGame.getListForStated()
    }

}