package com.hutapp.org.notes.hut.sudocucompose.domain.uscase

import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositoryModelSudoku

class GetListForStartedUseCase(private val repositoryModelSudoku: RepositoryModelSudoku) {
    operator fun invoke(): List<ModelSudoku> {
        return repositoryModelSudoku.getListForStated()
    }

}