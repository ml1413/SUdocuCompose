package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class GetListForStartedUseCase(private val repositorySudokuGame: RepositorySudokuGame) {
    operator fun invoke(): ModelSudoku {
        return repositorySudokuGame.getListForStated()
    }

}