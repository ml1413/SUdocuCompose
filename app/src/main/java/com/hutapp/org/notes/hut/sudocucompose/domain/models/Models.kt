package com.hutapp.org.notes.hut.sudocucompose.domain.models

import com.hutapp.org.notes.hut.sudocucompose.data.Room.EntityItemCell
import com.hutapp.org.notes.hut.sudocucompose.data.Room.EntityModelSudoku

data class ModelSudoku(
    val isVictory: Boolean = false,
    val hasSelectedCells: Boolean = false,
    val isHideSelected: Boolean = false,
    val isShowErrorAnswer: Boolean = false,
    val isShowAlmostAnswer: Boolean = false,
    val isShowCorrectAnswer: Boolean = false,
    val isShowAnimationHint: Boolean = false,
    val listItemCell: List<ItemCell>
)

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

fun ModelSudoku.mapToEntity() =
    EntityModelSudoku(
        isVictory = this.isVictory,
        hasSelectedCells = this.hasSelectedCells,
        isHideSelected = this.isHideSelected,
        isShowErrorAnswer = this.isShowErrorAnswer,
        isShowAlmostAnswer = this.isShowAlmostAnswer,
        isShowCorrectAnswer = this.isShowCorrectAnswer,
        isShowAnimationHint = this.isShowAnimationHint,
        entityListItemCell = this.listItemCell.map { itemCell -> itemCell.mapToEntity() }
    )

fun ItemCell.mapToEntity() =
    EntityItemCell(
        startedValue = this.startedValue,
        setValue = this.setValue,
        isStartedCell = this.isStartedCell,
        isSelected = this.isSelected,
        block = this.block,
        selectedCellIndex = this.selectedCellIndex,
        row = this.row,
        column = this.column,
        colorCell = this.colorCell.mapToEntity(),
        textStyle = this.textStyle.mapToEntity(),
        almostHintRow = this.almostHintRow.mapToEntity(),
        almostHintColumn = this.almostHintColumn.mapToEntity(),
        almostHintBlock = this.almostHintBlock.mapToEntity(),
    )

