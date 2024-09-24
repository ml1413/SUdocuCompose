package com.hutapp.org.notes.hut.sudocucompose.domain.moles

data class ModelSudoku(
    val selectedCell: ItemCell? = null,
    val listItemCell: List<ItemCell>
)

data class ItemCell(
    val numInCell: Int,
    val numFromSelectedCell: Int = -1,
    val isStartedCell: Boolean,
    val isSelected: Boolean = false,
    val selectedCellIndex: Int = -1,
    val selectedRow: Int = -1,
    val selectedCol: Int = -1,
)