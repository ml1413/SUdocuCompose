package com.hutapp.org.notes.hut.sudocucompose.data.repository

import com.hutapp.org.notes.hut.sudocucompose.data.Room.SudokuDB
import com.hutapp.org.notes.hut.sudocucompose.data.SudokuGames
import com.hutapp.org.notes.hut.sudocucompose.domain.models.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.models.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.models.mapToEntity
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame
import javax.inject.Inject

class RepositorySudokuGameImpl @Inject constructor(
    private val sudokuGames: SudokuGames,
    private val sudokuDB: SudokuDB
) : RepositorySudokuGame {
    override fun selectedCell(
        modelSudoku: ModelSudoku,
        itemCell: ItemCell
    ): ModelSudoku {
        return sudokuGames.selectedCell(
            modelSudoku = modelSudoku,
            itemCell = itemCell
        )
    }

    override fun onOffHideSelectedLineOnField(
        isHide: Boolean,
        modelSudoku: ModelSudoku
    ): ModelSudoku {
        return sudokuGames.onOffHideSelectedLineOnField(isHide = isHide, modelSudoku = modelSudoku)
    }

    override fun isShowErrorAnswer(isShowError: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return sudokuGames.isShowErrorAnswer(isShowError = isShowError, modelSudoku = modelSudoku)
    }

    override fun isShowAlmostAnswer(isHow: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return sudokuGames.isShowAlmostAnswer(isHow = isHow, modelSudoku = modelSudoku)
    }

    override fun isShowCorrectAnswer(isShow: Boolean, modelSudoku: ModelSudoku): ModelSudoku {
        return sudokuGames.isShowCorrectAnswer(isShow = isShow, modelSudoku = modelSudoku)
    }

    override fun onOffAnimationHint(
        isShowAnimationHint: Boolean,
        modelSudoku: ModelSudoku
    ): ModelSudoku {
        return sudokuGames.onOffAnimationHint(
            isShowAnimationHint = isShowAnimationHint,
            modelSudoku = modelSudoku
        )
    }

    override suspend fun saveInRoom(modelSudoku: ModelSudoku) {
        val entityModelSudoku = modelSudoku.mapToEntity()
        sudokuDB.getDao().apply {
            updateItem(entityModelSudoku = entityModelSudoku)
        }
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

    override fun unselectedCell(modelSudoku: ModelSudoku): ModelSudoku {
        return sudokuGames.unselectedCell(modelSudoku = modelSudoku)
    }
}