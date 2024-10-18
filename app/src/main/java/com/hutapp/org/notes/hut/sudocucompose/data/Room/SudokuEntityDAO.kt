package com.hutapp.org.notes.hut.sudocucompose.data.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hutapp.org.notes.hut.sudocucompose.data.Room.EntityModelSudoku.Companion.TABLE_NAME

@Dao
interface SudokuEntityDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItem(entityModelSudoku: EntityModelSudoku)

    @Query("select * from  $TABLE_NAME")
    suspend fun getSavedSudokuEntity():EntityModelSudoku
}