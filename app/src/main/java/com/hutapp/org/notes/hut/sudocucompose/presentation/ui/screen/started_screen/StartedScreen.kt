package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.started_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hutapp.org.notes.hut.sudocucompose.R

@Composable
fun StartedScreen(
    modifier: Modifier = Modifier,
    onChecked: (check: Boolean) -> Unit,
    onButtonClickListener: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            MyCheckBoxLine(
                modifier = modifier,
                text = stringResource(R.string.hide_select),
                onChecked = onChecked
            )
            //todo need add check show error
            Button(
                onClick = onButtonClickListener
            ) { Text(stringResource(R.string.start)) }
        }
    }
}

@Composable
private fun MyCheckBoxLine(
    modifier: Modifier,
    text: String,
    onChecked: (Boolean) -> Unit
) {
    val (checkedState, onStateChange) = remember { mutableStateOf(true) }
    Row(
        Modifier
            .fillMaxWidth()
            .toggleable(
                value = checkedState,
                onValueChange = {
                    onStateChange(!checkedState)
                    onChecked(it)
                },
                role = Role.Checkbox
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = modifier.padding(16.dp),
            checked = checkedState,
            onCheckedChange = null
        )
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}

