package com.hutapp.org.notes.hut.sudocucompose.domain.models

import com.hutapp.org.notes.hut.sudocucompose.data.Room.EntityAlmostHint
import com.hutapp.org.notes.hut.sudocucompose.data.Room.EntityColorCellEnum
import com.hutapp.org.notes.hut.sudocucompose.data.Room.EntityItemCell
import com.hutapp.org.notes.hut.sudocucompose.data.Room.EntityModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.data.Room.EntityTextStyleEnum

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

fun AlmostHint.mapToEntity() =
    when (this) {
        AlmostHint.INITIAL -> EntityAlmostHint.INITIAL
        AlmostHint.ROW -> EntityAlmostHint.ROW
        AlmostHint.COLUMN -> EntityAlmostHint.COLUMN
        AlmostHint.BLOCK -> EntityAlmostHint.BLOCK
    }

fun TextStyleEnum.mapToEntity() =
    when (this) {
        TextStyleEnum.UNSELECTED -> EntityTextStyleEnum.UNSELECTED
        TextStyleEnum.ON_STARTED_CELL -> EntityTextStyleEnum.ON_STARTED_CELL
        TextStyleEnum.SELECTED_IN_CELL -> EntityTextStyleEnum.SELECTED_IN_CELL
        TextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_NO_STARTED -> EntityTextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_NO_STARTED
        TextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_STARTED -> EntityTextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_STARTED
        TextStyleEnum.ALMOST -> EntityTextStyleEnum.ALMOST
        TextStyleEnum.ALL_IS_CORRECT -> EntityTextStyleEnum.ALL_IS_CORRECT
        TextStyleEnum.ERROR -> EntityTextStyleEnum.ERROR
    }
fun ColorCellEnum.mapToEntity()=
    when (this) {
        ColorCellEnum.UNSELECTED -> EntityColorCellEnum.UNSELECTED
        ColorCellEnum.SELECTED_CELL -> EntityColorCellEnum.SELECTED_CELL
        ColorCellEnum.SELECT_LINE -> EntityColorCellEnum.SELECT_LINE
        ColorCellEnum.STARTED_CELL_ON_LINE -> EntityColorCellEnum.STARTED_CELL_ON_LINE
        ColorCellEnum.COLOR_STARTED_CELL -> EntityColorCellEnum.COLOR_STARTED_CELL
        ColorCellEnum.SELECTED_BLOCK -> EntityColorCellEnum.SELECTED_BLOCK
    }
