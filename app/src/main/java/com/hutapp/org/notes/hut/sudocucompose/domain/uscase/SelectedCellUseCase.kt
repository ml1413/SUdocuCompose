package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.data.repository.RepositorySudokuGameImpl
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

class SelectedCellUseCase(private val repositorySudokuGameImpl: RepositorySudokuGameImpl) {
    operator fun invoke(
        listModelSudoku: List<ModelSudoku>?,
        index: Int,
        selectedRow: Int,
        selectedColum: Int,
        isSelected: Boolean
    ): List<ModelSudoku> {
        return repositorySudokuGameImpl.selectedCell(
            listModelSudoku = listModelSudoku,
            index = index,
            selectedRow = selectedRow,
            selectedColum = selectedColum,
            isSelected = isSelected
        )
    }
}