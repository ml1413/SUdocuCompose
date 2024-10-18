package com.hutapp.org.notes.hut.sudocucompose.data.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.hutapp.org.notes.hut.sudocucompose.data.Room.EntityModelSudoku.Companion.TABLE_NAME
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Entity(tableName = TABLE_NAME)
class EntityModelSudoku(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val isVictory: Boolean,
    val hasSelectedCells: Boolean,
    val isHideSelected: Boolean,
    val isShowErrorAnswer: Boolean,
    val isShowAlmostAnswer: Boolean,
    val isShowCorrectAnswer: Boolean,
    val isShowAnimationHint: Boolean,
    val entityListItemCell: List<EntityItemCell>
) {
    companion object {
        const val TABLE_NAME = "sudoku_table"
    }
}

@Serializable
data class EntityItemCell(
    val startedValue: Int,
    val setValue: Int,
    val isStartedCell: Boolean,
    val isSelected: Boolean,
    val block: Int,
    val selectedCellIndex: Int,
    val row: Int,
    val column: Int,
    val colorCell: EntityColorCellEnum,
    val textStyle: EntityTextStyleEnum,
    val almostHintRow: EntityAlmostHint,
    val almostHintColumn: EntityAlmostHint,
    val almostHintBlock: EntityAlmostHint,
)

@Serializable
enum class EntityAlmostHint {
    INITIAL, ROW, COLUMN, BLOCK
}

@Serializable
enum class EntityColorCellEnum() {
    UNSELECTED,
    SELECTED_CELL,
    SELECT_LINE,
    STARTED_CELL_ON_LINE,
    COLOR_STARTED_CELL,
    SELECTED_BLOCK
}

@Serializable
enum class EntityTextStyleEnum {
    UNSELECTED,
    ON_STARTED_CELL,
    SELECTED_IN_CELL,
    ON_SELECTED_LINE_OR_BLOCK_NO_STARTED,
    ON_SELECTED_LINE_OR_BLOCK_STARTED,
    ALMOST,
    ALL_IS_CORRECT,
    ERROR
}

class Converters {

    @TypeConverter
    fun fromModelList(value: List<EntityItemCell>): String {
        return Json.encodeToString(value = value)
    }

    @TypeConverter
    fun toModelList(value: String): List<EntityItemCell> {
        return Json.decodeFromString(value)
    }
}


