package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hutapp.org.notes.hut.sudocucompose.R
import com.hutapp.org.notes.hut.sudocucompose.presentation.CellViewModel
import kotlinx.coroutines.delay


@Composable
fun MyLazyGridForSudokuGameScreen(
    modifier: Modifier = Modifier,
    cellViewModel: CellViewModel,
    onBottomSheetDismissRequest:()->Unit,
    navigateOnScreenVictory: () -> Unit,
    onCheckedIsHideSelected: (check: Boolean) -> Unit,
    onCheckedIsShowErrorAnswer: (check: Boolean) -> Unit,
) {
    val sudokuViewModelState = cellViewModel.selectedCell.observeAsState().value
    if (sudokuViewModelState is CellViewModel.GameState.ResumeGame) {
        // hide selected after 20 t and seconds_____________________________________________________
        HideSelected(stateFromViewModel = sudokuViewModelState, cellViewModel = cellViewModel)
        //__________________________________________________________________________________________
        val openBottomSheet = rememberSaveable { mutableStateOf(false) }
        val colorGrid = MaterialTheme.colorScheme.onBackground

        Log.d("TAG1", "MyLazyGridForSudokuGameScreen: ")
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .wrapContentSize()

                    .border(
                        width = 1.dp,
                        color = colorGrid,
                        shape = RoundedCornerShape(12.dp, 12.dp, 16.dp, 16.dp)
                    )
            ) {

                SudokuTableGrid(
                    modifier = modifier,
                    stateFromViewModel = sudokuViewModelState,
                    colorGrid = colorGrid,
                    onCellClickListener = { index, selectedRow, selectedColum, isSelected ->
                        cellViewModel.selectedCell(
                            index = index,
                            selectedRow = selectedRow,
                            selectedColum = selectedColum,
                            isSelected = isSelected
                        )
                    }
                )
                MyBottomKeyBoard(
                    modifier = modifier,
                    onNumButtonClickListener = { value ->
                        cellViewModel.setValueInCell(value = value)
                    }
                )

            }
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(onClick = {
                    openBottomSheet.value = openBottomSheet.value.not()
                }) {
                    Icon(
                        modifier = modifier,
                        imageVector = Icons.Default.Settings, contentDescription = ""
                    )
                }
            }
            if (openBottomSheet.value) {
                MyBottomSheet(
                    sudokuViewModelState = sudokuViewModelState,
                    onDismissRequest = {
                        onBottomSheetDismissRequest()
                        openBottomSheet.value = false
                    },
                    onCheckedIsHideSelected = onCheckedIsHideSelected,
                    onCheckedIsShowErrorAnswer = onCheckedIsShowErrorAnswer
                )
            }
        }
    } else if (sudokuViewModelState is CellViewModel.GameState.Victory) {
        navigateOnScreenVictory()
    }
}


@Composable
private fun HideSelected(
    stateFromViewModel: CellViewModel.GameState.ResumeGame,
    cellViewModel: CellViewModel
) {
    if (stateFromViewModel.modelSudoku.isHideSelected && stateFromViewModel.modelSudoku.hasSelectedCells) {
        LaunchedEffect(stateFromViewModel) {
            delay(20_000)
            cellViewModel.unselectedCell()
        }
    }
}



