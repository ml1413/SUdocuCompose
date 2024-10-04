package com.hutapp.org.notes.hut.sudocucompose.presentation.DI

import com.hutapp.org.notes.hut.sudocucompose.data.SudokuGames
import com.hutapp.org.notes.hut.sudocucompose.data.repository.RepositorySudokuGameImpl
import com.hutapp.org.notes.hut.sudocucompose.domain.repository.RepositorySudokuGame
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.CheckAllAnswerUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.GetListForStartedUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.IsShowErrorAnswerUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.OnOffHideSelectedLineOnFieldUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.SelectedCellUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.SetValueInCellUseCase
import com.hutapp.org.notes.hut.sudocucompose.domain.uscase.UnSelectedCellUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Singleton
    @Provides
    fun provideSudokuGames(): SudokuGames {
        return SudokuGames()
    }

    @Singleton
    @Provides
    fun provideRepository(sudokuGames: SudokuGames): RepositorySudokuGame {
        return RepositorySudokuGameImpl(sudokuGames = sudokuGames)
    }

    @Provides
    fun provideCheckAllAnswerUseCase(repositorySudokuGame: RepositorySudokuGame): CheckAllAnswerUseCase {
        return CheckAllAnswerUseCase(repositorySudokuGame = repositorySudokuGame)
    }

    @Provides
    fun provideGetListForStartedUseCase(repositorySudokuGame: RepositorySudokuGame): GetListForStartedUseCase {
        return GetListForStartedUseCase(repositorySudokuGame = repositorySudokuGame)
    }

    @Provides
    fun provideSelectedCellUseCase(repositorySudokuGame: RepositorySudokuGame): SelectedCellUseCase {
        return SelectedCellUseCase(repositorySudokuGame = repositorySudokuGame)
    }

    @Provides
    fun provideSetValueInCellUseCase(repositorySudokuGame: RepositorySudokuGame): SetValueInCellUseCase {
        return SetValueInCellUseCase(repositorySudokuGame = repositorySudokuGame)
    }

    @Provides
    fun provideUnSelectedCellUseCase(repositorySudokuGame: RepositorySudokuGame): UnSelectedCellUseCase {
        return UnSelectedCellUseCase(repositorySudokuGame = repositorySudokuGame)
    }

    @Provides
    fun provideOnOffHideSelectedLineOnFieldUseCase(repositorySudokuGame: RepositorySudokuGame): OnOffHideSelectedLineOnFieldUseCase {
        return OnOffHideSelectedLineOnFieldUseCase(repositorySudokuGame = repositorySudokuGame)
    }

    @Provides
    fun provideIsShowErrorAnswerUseCase(repositorySudokuGame: RepositorySudokuGame): IsShowErrorAnswerUseCase {
        return IsShowErrorAnswerUseCase(repositorySudokuGame = repositorySudokuGame)
    }
}