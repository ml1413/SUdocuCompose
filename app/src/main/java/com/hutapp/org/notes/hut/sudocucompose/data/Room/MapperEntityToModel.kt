package com.hutapp.org.notes.hut.sudocucompose.data.Room

import com.hutapp.org.notes.hut.sudocucompose.domain.models.AlmostHint
import com.hutapp.org.notes.hut.sudocucompose.domain.models.ColorCellEnum
import com.hutapp.org.notes.hut.sudocucompose.domain.models.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.models.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.models.TextStyleEnum

fun EntityModelSudoku.mapToModel() =
    ModelSudoku(
        isVictory = this.isVictory,
        hasSelectedCells = this.hasSelectedCells,
        isHideSelected = this.isHideSelected,
        isShowErrorAnswer = this.isShowErrorAnswer,
        isShowAlmostAnswer = this.isShowAlmostAnswer,
        isShowCorrectAnswer = this.isShowCorrectAnswer,
        isShowAnimationHint = this.isShowAnimationHint,
        listItemCell = this.entityListItemCell.map { itemCell -> itemCell.mapToModel() }
    )

fun EntityItemCell.mapToModel() =
    ItemCell(
        startedValue = this.startedValue,
        setValue = this.setValue,
        isStartedCell = this.isStartedCell,
        isSelected = this.isSelected,
        block = this.block,
        selectedCellIndex = this.selectedCellIndex,
        row = this.row,
        column = this.column,
        colorCell = this.colorCell.mapToModel(),
        textStyle = this.textStyle.mapToModel(),
        almostHintRow = this.almostHintRow.mapToModel(),
        almostHintColumn = this.almostHintColumn.mapToModel(),
        almostHintBlock = this.almostHintBlock.mapToModel(),
    )

fun EntityAlmostHint.mapToModel() =
    when (this) {
        EntityAlmostHint.INITIAL -> AlmostHint.INITIAL
        EntityAlmostHint.ROW -> AlmostHint.ROW
        EntityAlmostHint.COLUMN -> AlmostHint.COLUMN
        EntityAlmostHint.BLOCK -> AlmostHint.BLOCK
    }

fun EntityTextStyleEnum.mapToModel() =
    when (this) {
        EntityTextStyleEnum.UNSELECTED -> TextStyleEnum.UNSELECTED
        EntityTextStyleEnum.ON_STARTED_CELL -> TextStyleEnum.ON_STARTED_CELL
        EntityTextStyleEnum.SELECTED_IN_CELL -> TextStyleEnum.SELECTED_IN_CELL
        EntityTextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_NO_STARTED -> TextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_NO_STARTED
        EntityTextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_STARTED -> TextStyleEnum.ON_SELECTED_LINE_OR_BLOCK_STARTED
        EntityTextStyleEnum.ALMOST -> TextStyleEnum.ALMOST
        EntityTextStyleEnum.ALL_IS_CORRECT -> TextStyleEnum.ALL_IS_CORRECT
        EntityTextStyleEnum.ERROR -> TextStyleEnum.ERROR
    }

fun EntityColorCellEnum.mapToModel() =
    when (this) {
        EntityColorCellEnum.UNSELECTED -> ColorCellEnum.UNSELECTED
        EntityColorCellEnum.SELECTED_CELL -> ColorCellEnum.SELECTED_CELL
        EntityColorCellEnum.SELECT_LINE -> ColorCellEnum.SELECT_LINE
        EntityColorCellEnum.STARTED_CELL_ON_LINE -> ColorCellEnum.STARTED_CELL_ON_LINE
        EntityColorCellEnum.COLOR_STARTED_CELL -> ColorCellEnum.COLOR_STARTED_CELL
        EntityColorCellEnum.SELECTED_BLOCK -> ColorCellEnum.SELECTED_BLOCK
    }
