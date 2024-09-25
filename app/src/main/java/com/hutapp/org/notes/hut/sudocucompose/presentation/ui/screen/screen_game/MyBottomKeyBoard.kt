package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// keyboard for set value in cell __________________________________________________________________
@Composable
fun MyBottomKeyBoard(
    modifier: Modifier,
    onNumButtonClickListener: (Int) -> Unit
) {
    val listNumber = (1..9).toList()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(listNumber) { value ->
            FloatingActionButton(
                modifier = modifier
                    .padding(8.dp)
                    .border(
                        width = 1.dp, color = MaterialTheme.colorScheme.onBackground,
                        shape = FloatingActionButtonDefaults.shape,
                    ),
                containerColor = MaterialTheme.colorScheme.background,
                onClick = {
                    onNumButtonClickListener(value)
                }
            ) {
                Text(text = "$value")
            }
        }
    }
}