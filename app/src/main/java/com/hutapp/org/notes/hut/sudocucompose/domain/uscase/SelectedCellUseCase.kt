package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.data.repository.RepositorySudokuGameImpl
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class SelectedCellUseCase(private val repositorySudokuGame: RepositorySudokuGame) {
    operator fun invoke(
        modelSudoku: ModelSudoku,
        itemCell:ItemCell
    ): ModelSudoku{
        return repositorySudokuGame.selectedCell(
            modelSudoku = modelSudoku,
            itemCell = itemCell
        )
    }
}