package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.started_screen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
    onButtonClickListener: () -> Unit
) {
    val stateCellViewModel = cellViewModel.selectedCell.observeAsState().value
    if (stateCellViewModel is CellViewModel.GameState.ResumeGame) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

            Button(
                modifier = modifier.padding(16.dp),
                onClick = onButtonClickListener
            ) { Text(stringResource(R.string.start)) }
        }
    }
}


