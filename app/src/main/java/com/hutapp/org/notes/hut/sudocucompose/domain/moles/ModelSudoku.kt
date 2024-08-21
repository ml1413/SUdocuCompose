package com.hutapp.org.notes.hut.sudocucompose.domain.moles

data class ModelSudoku(
    var selectedCell: Int = -1,
    var selectedRow: Int = -1,
    var selectedCol: Int = -1,
)