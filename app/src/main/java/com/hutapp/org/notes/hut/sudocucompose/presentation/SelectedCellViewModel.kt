package com.hutapp.org.notes.hut.sudocucompose.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hutapp.org.notes.hut.sudocucompose.data.RepositoryModelSudokuImpl
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.GetModelSudokuUseCase

class SelectedCellViewModel : ViewModel() {
    // todo need inject
    private val getModelSudokuUseCase =
        GetModelSudokuUseCase(repositoryModelSudoku = RepositoryModelSudokuImpl())

    private val _selectedCell = MutableLiveData<ModelSudoku>(null)
    val selectedCell: LiveData<ModelSudoku> = _selectedCell

    fun selectedCell(index: Int, selectedRow: Int, selectedColum: Int) {
        val modelSudoku = getModelSudokuUseCase(
            index = index,
            selectedRow = selectedRow,
            selectedColum = selectedColum
        )
        _selectedCell.value = modelSudoku
    }


}