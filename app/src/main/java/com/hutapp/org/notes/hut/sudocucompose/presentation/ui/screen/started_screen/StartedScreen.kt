package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.started_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hutapp.org.notes.hut.sudocucompose.R
import com.hutapp.org.notes.hut.sudocucompose.presentation.CellViewModel

@Composable
fun StartedScreen(
    modifier: Modifier = Modifier,
    cellViewModel: CellViewModel,
    onChecked: (check: Boolean) -> Unit,
    onButtonClickListener: () -> Unit
) {
    val stateCellViewModel = cellViewModel.selectedCell.observeAsState().value
    if (stateCellViewModel is CellViewModel.GameState.ResumeGame) {
        val listPariForLazy = listOf(
            stateCellViewModel.modelSudoku.isHideSelected to R.string.hide_select,
            stateCellViewModel.modelSudoku.isShowErrorAnswer to R.string.show_wrong_answer
        )
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = modifier.weight(1f),
                contentPadding = PaddingValues(vertical = 32.dp)
            ) {
                items(listPariForLazy) { pair ->
                    MyCheckBoxLine(
                        modifier = modifier,
                        text = stringResource(pair.second),
                        check = pair.first,
                        onChecked = onChecked
                    )

                }
            }
            Button(
                modifier = modifier.padding(16.dp),
                onClick = onButtonClickListener
            ) { Text(stringResource(R.string.start)) }
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Prew(modifier: Modifier = Modifier) {
    MaterialTheme {
        MyCheckBoxLine(
            modifier = modifier,
            true,
            "wer", onChecked = {}
        )
    }

}


