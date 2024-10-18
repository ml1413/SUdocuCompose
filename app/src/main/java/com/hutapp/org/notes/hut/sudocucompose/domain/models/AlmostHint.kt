package com.hutapp.org.notes.hut.sudocucompose.domain.models

import com.hutapp.org.notes.hut.sudocucompose.data.Room.EntityAlmostHint

enum class AlmostHint {
    INITIAL, ROW, COLUMN, BLOCK
}

fun AlmostHint.mapToEntity() =
    when (this) {
        AlmostHint.INITIAL -> EntityAlmostHint.INITIAL
        AlmostHint.ROW -> EntityAlmostHint.ROW
        AlmostHint.COLUMN -> EntityAlmostHint.COLUMN
        AlmostHint.BLOCK -> EntityAlmostHint.BLOCK
    }
