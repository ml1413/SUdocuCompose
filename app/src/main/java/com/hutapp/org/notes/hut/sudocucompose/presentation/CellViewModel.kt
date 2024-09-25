package com.hutapp.org.notes.hut.sudocucompose.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hutapp.org.notes.hut.sudocucompose.data.SudokuGames
import com.hutapp.org.notes.hut.sudocucompose.data.repository.RepositorySudokuGameImpl
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.CheckAllAnswerUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.GetListForStartedUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.SelectedCellUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.SetValueInCellUseCase

class CellViewModel : ViewModel() {
    // todo need inject
    private val sudokuGames = SudokuGames()
    private val repositorySudokuGameImpl = RepositorySudokuGameImpl(sudokuGames)
    private val getModelSudokuUseCase =
        SelectedCellUseCase(repositorySudokuGameImpl = repositorySudokuGameImpl)
    private val getListForStartedUseCase =
        GetListForStartedUseCase(repositorySudokuGame = repositorySudokuGameImpl)
    private val setValueInCellUseCase =
        SetValueInCellUseCase(repositorySudokuGameImpl = repositorySudokuGameImpl)
    private val checkAllAnswerUseCase =
        CheckAllAnswerUseCase(repositorySudokuGame = repositorySudokuGameImpl)

    private val _selectedCell = MutableLiveData<GameState>(GameState.Initial)
    val selectedCell: LiveData<GameState> = _selectedCell

    init {
        val modelSudoku = getListForStartedUseCase()
        _selectedCell.value = GameState.ResumeGame(modelSudoku = modelSudoku)
    }

    fun selectedCell(index: Int, selectedRow: Int, selectedColum: Int, isSelected: Boolean) {
        val stateGame = _selectedCell.value
        if (stateGame is GameState.ResumeGame) {
            val modelSudoku = getModelSudokuUseCase(
                modelSudoku = stateGame.modelSudoku,
                index = index,
                selectedRow = selectedRow,
                selectedColum = selectedColum,
                isSelected = isSelected
            )
            _selectedCell.value = GameState.ResumeGame(modelSudoku = modelSudoku)
        }
    }

    fun setValueInCell(value: Int) {
        val stateGame = _selectedCell.value
        if (stateGame is GameState.ResumeGame) {
            val newModelSudoku =
                setValueInCellUseCase(value = value, modelSudoku = stateGame.modelSudoku)
            val isAllAnswerCorrect = checkAllAnswerUseCase(modelSudoku = newModelSudoku)

            checkIsVictory(isAllAnswerCorrect, newModelSudoku)
        }
    }


    sealed class GameState() {
        object Initial : GameState()
        class ResumeGame(val modelSudoku: ModelSudoku) : GameState()
        object Victory : GameState()
    }

    /** other fun_________________________________________________________________________________*/
    private fun checkIsVictory(
        isAllAnswerCorrect: ModelSudoku,
        newModelSudoku: ModelSudoku
    ) {
        if (isAllAnswerCorrect.isVictory) {
            _selectedCell.value = GameState.Victory
        } else {
            _selectedCell.value = GameState.ResumeGame(modelSudoku = newModelSudoku)
        }
    }

}