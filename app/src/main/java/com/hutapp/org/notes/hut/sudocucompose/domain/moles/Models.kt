package com.hutapp.org.notes.hut.sudocucompose.domain.moles

data class ModelSudoku(
    val isVictory: Boolean = false,
    val isHideSelected: Boolean = true,
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
    val textStyle: TextStyleEnum = TextStyleEnum.UNSELECTED
)

enum class ColorCellEnum() {
    UNSELECTED, SELECTED_CELL, SELECT_LINE, COLOR_STARTED_CELL, SELECTED_BLOCK
}

enum class TextStyleEnum {
    UNSELECTED, ON_STARTED_CELL, SELECTED_IN_CELL, ON_SELECTED_LINE_OR_BLOCK, ERROR
}