package com.hutapp.org.notes.hut.sudocucompose.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hutapp.org.notes.hut.sudocucompose.domain.models.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.models.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.CheckAllAnswerUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.GetListForStartedUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.IsHowAlmostAnswerUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.IsShowAnimationHintUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.IsShowCorrectAnswerUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.IsShowErrorAnswerUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.OnOffHideSelectedLineOnFieldUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.SaveInRoomUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.SelectedCellUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.SetValueInCellUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.UnSelectedCellUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CellViewModel @Inject constructor(
    getListForStartedUseCase: GetListForStartedUseCase,
    private val getModelSudokuUseCase: SelectedCellUseCase,
    private val setValueInCellUseCase: SetValueInCellUseCase,
    private val checkAllAnswerUseCase: CheckAllAnswerUseCase,
    private val unSelectedCellUseCase: UnSelectedCellUseCase,
    private val onOffHideSelectedLineOnFieldUseCase: OnOffHideSelectedLineOnFieldUseCase,
    private val isShowErrorAnswerUseCase: IsShowErrorAnswerUseCase,
    private val isHowAlmostAnswerUseCase: IsHowAlmostAnswerUseCase,
    private val isShowCorrectAnswerUseCase: IsShowCorrectAnswerUseCase,
    private val isShowAnimationHintUseCase: IsShowAnimationHintUseCase,
    private val saveInRoomUseCase: SaveInRoomUseCase
) : ViewModel() {


    private val _selectedCell = MutableLiveData<GameState>(GameState.Initial)
    val selectedCell: LiveData<GameState> = _selectedCell

    init {
        val modelSudoku = getListForStartedUseCase()
        _selectedCell.value = GameState.ResumeGame(modelSudoku = modelSudoku)
    }

    fun selectedCell(itemCell: ItemCell) {
        _selectedCell.value?.checkCurrentState { modelSudoku ->

            val newModel = getModelSudokuUseCase(
                modelSudoku = modelSudoku,
                itemCell = itemCell
            )
            _selectedCell.value = GameState.ResumeGame(modelSudoku = newModel)
        }
    }

    fun setValueInCell(value: Int) {
        _selectedCell.value?.checkCurrentState { modelSudoku ->
            //set value in model
            val newModelSudoku =
                setValueInCellUseCase(value = value, modelSudoku = modelSudoku)
            // check app answer
            val isAllAnswerCorrect = checkAllAnswerUseCase(modelSudoku = newModelSudoku).isVictory
            _selectedCell.value =
                if (isAllAnswerCorrect) GameState.Victory else GameState.ResumeGame(modelSudoku = newModelSudoku)
        }
        // todo temp test invoke
        saveInRoom()
    }

    fun unselectedCell() {
        _selectedCell.value?.checkCurrentState { modelSudoku ->
            val newModelSudoku = unSelectedCellUseCase(modelSudoku = modelSudoku)
            _selectedCell.value = GameState.ResumeGame(modelSudoku = newModelSudoku)
        }
    }

    fun onOffHideSelected(isHide: Boolean) {
        _selectedCell.value?.checkCurrentState { modelSudoku ->
            val newModel = onOffHideSelectedLineOnFieldUseCase(
                isHide = isHide,
                modelSudoku = modelSudoku
            )
            _selectedCell.value = GameState.ResumeGame(modelSudoku = newModel)
        }
    }

    fun onOffAlmostAnswer(isHow: Boolean) {
        _selectedCell.value?.checkCurrentState { modelSudoku ->
            val newModel = isHowAlmostAnswerUseCase(isHow = isHow, modelSudoku = modelSudoku)
            _selectedCell.value = GameState.ResumeGame(modelSudoku = newModel)
        }
    }

    fun isShowErrorAnswer(isShowError: Boolean) {
        _selectedCell.value?.checkCurrentState { modelSudoku ->
            val newModel =
                isShowErrorAnswerUseCase(isShowError = isShowError, modelSudoku = modelSudoku)
            _selectedCell.value = GameState.ResumeGame(modelSudoku = newModel)
        }
    }

    fun onOffCorrectAnswer(isShow: Boolean) {
        _selectedCell.value?.checkCurrentState { modelSudoku ->
            val newModel =
                isShowCorrectAnswerUseCase(isShow = isShow, modelSudoku = modelSudoku)
            _selectedCell.value = GameState.ResumeGame(modelSudoku = newModel)
        }
    }

    fun onOffAnimationHint(isShowAnimationHint: Boolean) {
        _selectedCell.value?.checkCurrentState() { modelSudoku ->
            val newModel = isShowAnimationHintUseCase(
                isShowAnimationHint = isShowAnimationHint,
                modelSudoku = modelSudoku
            )
            _selectedCell.value = GameState.ResumeGame(modelSudoku = newModel)
        }
    }

    fun saveInRoom() {
        _selectedCell.value?.checkCurrentState { modelSudoku ->
            viewModelScope.launch {
                saveInRoomUseCase(modelSudoku = modelSudoku)
            }
        }
    }

    sealed class GameState() {
        object Initial : GameState()
        class ResumeGame(val modelSudoku: ModelSudoku) : GameState()
        object Victory : GameState()
    }

    /** other fun_________________________________________________________________________________*/
    private fun GameState.checkCurrentState(
        stateIsInitial: () -> Unit = {},
        stateIsVictory: () -> Unit = {},
        stateIsGame: (ModelSudoku) -> Unit = {},
    ) {
        when (this) {
            GameState.Initial -> stateIsInitial()
            is GameState.ResumeGame -> stateIsGame(this.modelSudoku)
            GameState.Victory -> stateIsVictory()
        }
    }
}