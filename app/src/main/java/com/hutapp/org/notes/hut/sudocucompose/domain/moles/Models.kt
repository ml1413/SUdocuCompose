package com.hutapp.org.notes.hut.sudocucompose.domain.moles

data class ModelSudoku(
    val isVictory: Boolean = false,
    val hasSelectedCells: Boolean = false,
    val isHideSelected: Boolean = false,
    val isShowErrorAnswer: Boolean = false,
    val listItemCell: List<ItemCell>
)

data class ItemCell(
    val startedValue: Int,
    val setValue: Int = -1,
    val isStartedCell: Boolean,
    val isSelected: Boolean = false,
    val selectedCellIndex: Int = -1,
    val row: Int = -1,
    val column: Int = -1,
    val colorCell: ColorCellEnum = ColorCellEnum.UNSELECTED,
    val textStyle: TextStyleEnum = TextStyleEnum.UNSELECTED
)

