package com.hutapp.org.notes.hut.sudocucompose.presentation.ui.screen.screen_game

import com.hutapp.org.notes.hut.sudocucompose.domain.models.ItemCell

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





