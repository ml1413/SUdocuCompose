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
