package com.hutapp.org.notes.hut.sudocucompose.domain.moles

data class ModelSudoku(
    var numInCell: Int,
    var numFromSelectedCell: Int = -1,
    val isStartedCell: Boolean,
    var isSelected: Boolean = false,
    var selectedCellIndex: Int = -1,
    var selectedRow: Int = -1,
    var selectedCol: Int = -1,
)