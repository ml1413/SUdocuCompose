package com.hutapp.org.notes.hut.sudocucompose.data.repository

import com.hutapp.org.notes.hut.sudocucompose.data.SudokuGames
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class RepositorySudokuGameImpl(private val sudokuGames: SudokuGames) : RepositorySudokuGame {
    override fun selectedCell(
        modelSudoku: ModelSudoku,
        index: Int,
        selectedRow: Int,
        selectedColum: Int,
        isSelected: Boolean
    ): ModelSudoku {
        return sudokuGames.selectedCell(
            modelSudoku = modelSudoku,
            index = index,
            selectedRow = selectedRow,
            selectedColum = selectedColum,
            isSelected = isSelected
        )
    }

    override fun getListForStated(): ModelSudoku {
        return sudokuGames.getListModelSudoku()
    }

    override fun setValueInCell(value: Int, modelSudoku: ModelSudoku): ModelSudoku {
        return sudokuGames.setValueInCell(value = value, modelSudoku = modelSudoku)
    }

    override fun checkAllAnswer(modelSudoku: ModelSudoku): ModelSudoku {
        return sudokuGames.checkAllAnswer(modelSudoku = modelSudoku)
    }
}