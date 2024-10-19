package com.hutapp.org.notes.hut.sudocucompose.domain.models

import com.hutapp.org.notes.hut.sudocucompose.data.Room.ModelOrEntity

data class ModelSudoku(
    val isVictory: Boolean = false,
    val hasSelectedCells: Boolean = false,
    val isHideSelected: Boolean = false,
    val isShowErrorAnswer: Boolean = false,
    val isShowAlmostAnswer: Boolean = false,
    val isShowCorrectAnswer: Boolean = false,
    val isShowAnimationHint: Boolean = false,
    val listItemCell: List<ItemCell>
) : ModelOrEntity

data class ItemCell(
    val startedValue: Int,
    val setValue: Int = -1,
    val isStartedCell: Boolean,
    val isSelected: Boolean = false,
    val block: Int,
    val selectedCellIndex: Int = -1,
    val row: Int = -1,
    val column: Int = -1,
    val colorCell: ColorCellEnum = ColorCellEnum.UNSELECTED,
    val textStyle: TextStyleEnum = TextStyleEnum.UNSELECTED,
    val almostHintRow: AlmostHint = AlmostHint.INITIAL,
    val almostHintColumn: AlmostHint = AlmostHint.INITIAL,
    val almostHintBlock: AlmostHint = AlmostHint.INITIAL,

    )


