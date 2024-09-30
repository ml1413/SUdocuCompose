package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ItemCell
import com.hutapp.org.notes.hut.sudocucompose.domain.moles.ModelSudoku

/**  OTHER FUN __________________________________________________________________________________*/
fun getTestForCell(itemItemCell: ItemCell): String {
    return itemItemCell.let {
        if (it.isStartedCell)
            it.startedValue.toString()
        else
            if (it.setValue < 1) ""
            else it.setValue.toString()
    }
}





