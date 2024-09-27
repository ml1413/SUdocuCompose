package com.hutapp.org.notes.hut.sudocucompose.domain.moles

data class ModelSudoku(
    val isVictory: Boolean = false,
    val selectedCell: ItemCell? = null,
    val listItemCell: List<ItemCell>
)

data class ItemCell(
    val startedValue: Int,
    val setValue: Int = -1,
    val isStartedCell: Boolean,
    val isSelected: Boolean = false,
    val selectedCellIndex: Int = -1,
    val selectedRow: Int = -1,
    val selectedCol: Int = -1,
    val color: ColorEnum = ColorEnum.UNSELECTED
)

enum class ColorEnum() {
    UNSELECTED, SELECTED_CELL, SELECT_LINE,COLOR_STARTED_CELL
}