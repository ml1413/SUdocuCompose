package com.hutapp.org.notes.hut.sudocucompose.domain.models

import com.hutapp.org.notes.hut.sudocucompose.data.Room.EntityColorCellEnum

enum class ColorCellEnum() {
    UNSELECTED,
    SELECTED_CELL,
    SELECT_LINE,
    STARTED_CELL_ON_LINE,
    COLOR_STARTED_CELL,
    SELECTED_BLOCK
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
