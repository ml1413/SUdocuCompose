package com.hutapp.org.notes.hut.sudocucompose.data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [EntityModelSudoku::class], version = 1)
@TypeConverters(MyTypeConverters::class)
abstract class SudokuDB : RoomDatabase() {
    abstract fun getDao(): SudokuEntityDAO

    companion object {
        const val NAME_DB_SUDOKU = "name_db"
    }
}