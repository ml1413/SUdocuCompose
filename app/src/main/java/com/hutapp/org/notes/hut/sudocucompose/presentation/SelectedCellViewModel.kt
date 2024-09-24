package com.hutapp.org.notes.hut.sudocucompose.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hutapp.org.notes.hut.sudocucompose.data.SudokuGames
import com.hutapp.org.notes.hut.sudocucompose.data.repository.RepositorySudokuGameImpl
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.GetListForStartedUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.SelectedCellUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.SetValueInCellUseCase

class SelectedCellViewModel : ViewModel() {
    // todo need inject
    private val sudokuGames = SudokuGames()
    private val repositorySudokuGameImpl = RepositorySudokuGameImpl(sudokuGames)
    private val getModelSudokuUseCase =
        SelectedCellUseCase(repositorySudokuGameImpl = repositorySudokuGameImpl)
    private val getListForStartedUseCase =
        GetListForStartedUseCase(repositorySudokuGame = repositorySudokuGameImpl)
    private val setValueInCellUseCase =
        SetValueInCellUseCase(repositorySudokuGameImpl = repositorySudokuGameImpl)

    private val _selectedCell = MutableLiveData<GameState>(GameState.Initial)
    val selectedCell: LiveData<GameState> = _selectedCell

    init {
        val list = getListForStartedUseCase()
        Log.d("TAG1", ": $list")
        _selectedCell.value = GameState.ResumeGame(list = list)
    }

    fun selectedCell(index: Int, selectedRow: Int, selectedColum: Int, isSelected: Boolean) {
        val stateGame = _selectedCell.value
        if (stateGame is GameState.ResumeGame) {
            val listModelSudoku = getModelSudokuUseCase(
                listModelSudoku = stateGame.list,
                index = index,
                selectedRow = selectedRow,
                selectedColum = selectedColum,
                isSelected = isSelected
            )
            _selectedCell.value = GameState.ResumeGame(list = listModelSudoku)
        }
    }

    fun setValueInCell(value: Int) {
        val stateGame = _selectedCell.value
        if (stateGame is GameState.ResumeGame) {
            val newList = setValueInCellUseCase(value = value, list = stateGame.list)
            _selectedCell.value = GameState.ResumeGame(list = newList)
        }
    }

    sealed class GameState() {
        object Initial : GameState()
        class ResumeGame(val list: List<ModelSudoku>) : GameState()
        object Victory : GameState()
    }


}