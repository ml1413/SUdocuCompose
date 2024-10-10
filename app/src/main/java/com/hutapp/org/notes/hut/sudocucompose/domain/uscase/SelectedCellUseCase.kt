package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.models.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.models.ModelSudoku
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