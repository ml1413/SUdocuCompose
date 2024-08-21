package com.hutapp.org.notes.hut.sudocucompose.domain.repository

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

interface RepositoryModelSudoku {
    fun getModelSudoku(index: Int, selectedRow: Int, selectedColum: Int): ModelSudoku
}