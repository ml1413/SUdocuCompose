package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ScreenVictory(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Victory")
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun Preview(modifier: Modifier = Modifier) {
    ScreenVictory()
}