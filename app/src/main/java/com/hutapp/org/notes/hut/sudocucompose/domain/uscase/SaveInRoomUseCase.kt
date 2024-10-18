package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.models.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame

class SaveInRoomUseCase (private val repositorySudokuGame: RepositorySudokuGame){

    suspend operator fun invoke(modelSudoku: ModelSudoku) {
        repositorySudokuGame.saveInRoom(modelSudoku = modelSudoku)
    }
}