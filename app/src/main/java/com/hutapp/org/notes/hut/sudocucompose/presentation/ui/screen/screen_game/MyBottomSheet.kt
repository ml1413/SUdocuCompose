package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hutapp.org.notes.hut.sudocucompose.R
import com.hutapp.org.notes.hut.sudocucompose.presentation.CellViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyBottomSheet(
    modifier: Modifier = Modifier,
    sudokuViewModelState: CellViewModel.GameState.ResumeGame,
    onCheckedIsHideSelected: (check: Boolean) -> Unit,
    onCheckedIsShowErrorAnswer: (check: Boolean) -> Unit,
    onDismissRequest: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest =  onDismissRequest,
        sheetState = bottomSheetState,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = modifier.weight(1f),
            contentPadding = PaddingValues(vertical = 32.dp)
        ) {
            item {
                MyCheckBoxLine(
                    modifier = modifier,
                    text = stringResource(R.string.hide_select),
                    check = sudokuViewModelState.modelSudoku.isHideSelected,
                    onChecked = { isHide ->
                        onCheckedIsHideSelected(isHide)
                    }
                )
            }
            item {
                MyCheckBoxLine(
                    modifier = modifier,
                    text = stringResource(R.string.show_wrong_answer),
                    check = sudokuViewModelState.modelSudoku.isShowErrorAnswer,
                    onChecked = { isShowError ->
                        onCheckedIsShowErrorAnswer(isShowError)
                    }
                )
            }

        }
    }
}

@Composable
private fun MyCheckBoxLine(
    modifier: Modifier,
    check: Boolean,
    text: String,
    onChecked: (Boolean) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp, 2.dp, 16.dp)
            .border(
                1.dp,
                shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.onBackground
            )
            .toggleable(
                value = check,
                onValueChange = {
                    onChecked(check.not())
                },
                role = Role.Checkbox
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = modifier.padding(16.dp),
            checked = check,
            onCheckedChange = null
        )
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}