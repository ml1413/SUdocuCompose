package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hutapp.org.notes.hut.sudocucompose.domain.models.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.presentation.CellViewModel
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.MyLifeCycle
import kotlinx.coroutines.delay


@Composable
fun MyLazyGridForSudokuGameScreen(
    modifier: Modifier = Modifier,
    stateCellViewModel: State<CellViewModel.GameState?>,
    onLaunchListenerUnselected: () -> Unit,
    onBottomSheetDismissRequest: () -> Unit,
    navigateOnScreenVictory: () -> Unit,
    onCheckedIsHideSelected: (check: Boolean) -> Unit,
    onCheckedIsShowErrorAnswer: (check: Boolean) -> Unit,
    onCheckIsShowAlmostAnswer: (Boolean) -> Unit,
    onCheckIsShowAllAnswerCorrect: (Boolean) -> Unit,
    onCheckIsShowAnimationHint: (Boolean) -> Unit,
    onCellClickListener: (ItemCell) -> Unit,
    onNumButtonClickListener: (Int) -> Unit,
    onPauseListened: () -> Unit
) {
    val state = stateCellViewModel.value
    if (state is CellViewModel.GameState.ResumeGame) {
        //lifeCycle listener
        MyLifeCycle(onPause = { onPauseListened() })
        // hide selected after 20 t and seconds_____________________________________________________
        HideSelected(
            stateFromViewModel = state,
            onLaunchListenerUnselected = onLaunchListenerUnselected
        )
        //__________________________________________________________________________________________
        val openBottomSheet = rememberSaveable { mutableStateOf(false) }
        val colorGrid = MaterialTheme.colorScheme.onBackground
        val shape12Dp = RoundedCornerShape(12.dp, 12.dp, 16.dp, 16.dp)
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
                        shape = shape12Dp
                    )
            ) {

                SudokuTableGrid(
                    modifier = modifier,
                    stateFromViewModel = state,
                    colorGrid = colorGrid,
                    onCellClickListener = { itemCell ->
                        onCellClickListener(itemCell)
                    }
                )
                MyBottomKeyBoard(
                    modifier = modifier,
                    onNumButtonClickListener = { value ->
                        onNumButtonClickListener(value)
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
                    sudokuViewModelState = state,
                    onDismissRequest = {
                        onBottomSheetDismissRequest()
                        openBottomSheet.value = false
                    },
                    onCheckedIsHideSelected = onCheckedIsHideSelected,
                    onCheckedIsShowErrorAnswer = onCheckedIsShowErrorAnswer,
                    onCheckIsShowAlmostAnswer = onCheckIsShowAlmostAnswer,
                    onCheckIsShowAllAnswerCorrect = onCheckIsShowAllAnswerCorrect,
                    onCheckIsShowAnimationHint = onCheckIsShowAnimationHint
                )
            }
        }
    } else if (state is CellViewModel.GameState.Victory) {
        navigateOnScreenVictory()
    }
}


@Composable
private fun HideSelected(
    stateFromViewModel: CellViewModel.GameState.ResumeGame,
    onLaunchListenerUnselected: () -> Unit
) {
    if (stateFromViewModel.modelSudoku.isHideSelected && stateFromViewModel.modelSudoku.hasSelectedCells) {
        LaunchedEffect(stateFromViewModel) {
            delay(20_000)
            onLaunchListenerUnselected()
        }
    }
}



