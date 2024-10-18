package com.hutapp.org.notes.hut.sudocucompose.domain.models

import com.hutapp.org.notes.hut.sudocucompose.data.Room.EntityTextStyleEnum


enum class TextStyleEnum {
    UNSELECTED,
    ON_STARTED_CELL,
    SELECTED_IN_CELL,
    ON_SELECTED_LINE_OR_BLOCK_NO_STARTED,
    ON_SELECTED_LINE_OR_BLOCK_STARTED,
    ALMOST,
    ALL_IS_CORRECT,
    ERROR
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