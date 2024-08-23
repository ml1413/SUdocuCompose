package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

class GetListModelSudokuUseCase() {
    operator fun invoke(
        listModelSudoku: List<ModelSudoku>?,
        index: Int,
        selectedRow: Int,
        selectedColum: Int,
        isSelected: Boolean
    ): List<ModelSudoku> {
        val newListCells = listModelSudoku
            ?.map {
                if (it.isSelected) it.copy(isSelected = false)
                else it
            }
            ?.map { modelSudoku ->
                if (modelSudoku.selectedCellIndex == index)
                    modelSudoku.copy(
                        selectedCol = selectedColum,
                        selectedRow = selectedRow,
                        isSelected = isSelected
                    )
                else modelSudoku
            }
        return newListCells ?: emptyList()
    }
}