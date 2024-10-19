package com.hutapp.org.notes.hut.sudocucompose.data.Room

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MyTypeConverters {

    @TypeConverter
    fun fromModelList(value: List<EntityItemCell>): String {
        return Json.encodeToString(value = value)
    }

    @TypeConverter
    fun toModelList(value: String): List<EntityItemCell> {
        return Json.decodeFromString(value)
    }
}