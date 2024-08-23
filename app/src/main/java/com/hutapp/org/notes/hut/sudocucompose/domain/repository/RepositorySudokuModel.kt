package com.hutapp.org.notes.hut.sudocucompose.domain.repository

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

interface RepositoryModelSudoku {
    fun getListForStated(): List<ModelSudoku>
}