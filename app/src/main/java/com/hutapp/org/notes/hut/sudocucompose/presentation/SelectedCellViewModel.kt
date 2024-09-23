package com.hutapp.org.notes.hut.sudocucompose.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hutapp.org.notes.hut.sudocucompose.data.SudokuGames
import com.hutapp.org.notes.hut.sudocucompose.data.repository.RepositorySudokuGameImpl
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.GetListForStartedUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.GetListModelSudokuUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.SetValueInCellUseCase

class SelectedCellViewModel : ViewModel() {
    // todo need inject
    private val sudokuGames = SudokuGames()
    private val repositoryListModelSudokuImpl = RepositorySudokuGameImpl(sudokuGames)
    private val getModelSudokuUseCase =
        GetListModelSudokuUseCase()
    private val getListForStartedUseCase =
        GetListForStartedUseCase(repositorySudokuGame = repositoryListModelSudokuImpl)
    private val setValueInCellUseCase =
        SetValueInCellUseCase(repositorySudokuGameImpl = repositoryListModelSudokuImpl)

    private val _selectedCell = MutableLiveData<List<ModelSudoku>>()
    val selectedCell: LiveData<List<ModelSudoku>> = _selectedCell

    init {
        val list = getListForStartedUseCase()
        Log.d("TAG1", ": $list")
        _selectedCell.value = list
    }

    fun selectedCell(index: Int, selectedRow: Int, selectedColum: Int, isSelected: Boolean) {
        val modelSudoku = getModelSudokuUseCase(
            listModelSudoku = _selectedCell.value,
            index = index,
            selectedRow = selectedRow,
            selectedColum = selectedColum,
            isSelected = isSelected
        )
        _selectedCell.value = modelSudoku
    }

    fun setValueInCell(value: Int) {
        val newList = setValueInCellUseCase(value = value, list = _selectedCell.value)
        _selectedCell.value = newList
    }


}