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
    val colorCell: ColorCellEnum = ColorCellEnum.UNSELECTED,
    val colorText: ColorTextEnum = ColorTextEnum.UNSELECTED
)

enum class ColorCellEnum() {
    UNSELECTED, SELECTED_CELL, SELECT_LINE, COLOR_STARTED_CELL, SELECTED_BLOCK
}

enum class ColorTextEnum {
    UNSELECTED, ON_STARTED, SELECTED_IN_CELL, ON_SELECTED_LINE_OR_BLOCK, ERROR
}