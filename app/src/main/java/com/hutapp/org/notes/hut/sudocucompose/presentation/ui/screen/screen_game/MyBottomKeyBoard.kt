package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// keyboard for set value in cell __________________________________________________________________
@Composable
fun MyBottomKeyBoard(
    modifier: Modifier,
    onNumButtonClickListener: (Int) -> Unit
) {
    val shape = RoundedCornerShape(12.dp)
    val listNumber = (1..9).toList()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(listNumber) { value ->
            FloatingActionButton(
                modifier = modifier
                    .padding(12.dp)
                        ,
                shape = shape,
                containerColor = MaterialTheme.colorScheme.onBackground,
                onClick = {
                    onNumButtonClickListener(value)
                }
            ) {
                Text(text = "$value", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.background)
            }
        }
    }
}