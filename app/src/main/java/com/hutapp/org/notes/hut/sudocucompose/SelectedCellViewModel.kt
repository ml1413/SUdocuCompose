package com.hutapp.org.notes.hut.sudocucompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

class SelectedCellViewModel : ViewModel() {
    private val _selectedCell = MutableLiveData<ModelSudoku>(null)
    val selectedCell: LiveData<ModelSudoku> = _selectedCell

    fun selectedCell(index: Int, row: Int, colum: Int) {
        val modelSudoku = ModelSudoku(
            selectedCell = index,
            selectedRow = row,
            selectedCol = colum
        )
        _selectedCell.value = modelSudoku
    }


}