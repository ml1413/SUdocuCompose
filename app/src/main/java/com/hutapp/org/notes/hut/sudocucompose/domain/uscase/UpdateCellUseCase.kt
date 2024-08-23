package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

class UpdateCellUseCase {
    operator fun invoke(value: Int, list: List<ModelSudoku>?): List<ModelSudoku> {

        val newList = list?.map { modelSudoku ->
            if (modelSudoku.isSelected) {
                modelSudoku.copy(intValue = value)
            } else
                modelSudoku
        }
        return newList ?: emptyList()
    }
}