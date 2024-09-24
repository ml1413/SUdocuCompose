package com.hutapp.org.notes.hut.sudocucompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.sudokuTaple.MyLazyGridForSudokuScreen
import com.hutapp.org.notes.hut.sudocucompose.presentation.ui.theme.SUdocuComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SUdocuComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyLazyGridForSudokuScreen(CellViewModel = viewModel())
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview(modifier: Modifier = Modifier) {
    SUdocuComposeTheme {
        MyLazyGridForSudokuScreen(CellViewModel = viewModel())
    }
}
