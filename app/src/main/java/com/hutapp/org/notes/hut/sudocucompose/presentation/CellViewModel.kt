package com.hutapp.org.notes.hut.sudocucompose.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.CheckAllAnswerUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.GetListForStartedUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.OnOffHideSelectedLineOnFieldUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.SelectedCellUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.SetValueInCellUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.UnSelectedCellUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CellViewModel @Inject constructor(
    getListForStartedUseCase: GetListForStartedUseCase,
    private val getModelSudokuUseCase: SelectedCellUseCase,
    private val setValueInCellUseCase: SetValueInCellUseCase,
    private val checkAllAnswerUseCase: CheckAllAnswerUseCase,
    private val unSelectedCellUseCase: UnSelectedCellUseCase,
    private val onOffHideSelectedLineOnFieldUseCase: OnOffHideSelectedLineOnFieldUseCase
) : ViewModel() {


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
            //set value in model
            val newModelSudoku =
                setValueInCellUseCase(value = value, modelSudoku = stateGame.modelSudoku)
            // check app answer
            val isAllAnswerCorrect = checkAllAnswerUseCase(modelSudoku = newModelSudoku)
            checkIsVictory(isAllAnswerCorrect, newModelSudoku)
        }
    }

    fun unselectedCell() {
        val stateGame = _selectedCell.value
        if (stateGame is GameState.ResumeGame) {
            val newModelSudoku = unSelectedCellUseCase(modelSudoku = stateGame.modelSudoku)
            _selectedCell.value = GameState.ResumeGame(modelSudoku = newModelSudoku)
        }

    }

    fun onOffHideSelected(isHide: Boolean) {
        val state = _selectedCell.value
        if (state is GameState.ResumeGame) {
            val newModel = onOffHideSelectedLineOnFieldUseCase(
                isHide = isHide,
                modelSudoku = state.modelSudoku
            )
            _selectedCell.value = GameState.ResumeGame(modelSudoku = newModel)
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