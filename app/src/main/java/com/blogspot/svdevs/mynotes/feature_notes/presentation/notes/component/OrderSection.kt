package com.blogspot.svdevs.mynotes.feature_notes.presentation.notes.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.blogspot.svdevs.mynotes.feature_notes.domain.utils.NoteOrderBy
import com.blogspot.svdevs.mynotes.feature_notes.domain.utils.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrderBy: NoteOrderBy = NoteOrderBy.Date(OrderType.Descending),
    onOrderChange: (NoteOrderBy) -> Unit
) {

    Column(
        modifier = modifier
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(text = "Title",
                selected = noteOrderBy is NoteOrderBy.Title,
                onSelected = { onOrderChange(NoteOrderBy.Title(noteOrderBy.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Date",
                selected = noteOrderBy is NoteOrderBy.Date,
                onSelected = { onOrderChange(NoteOrderBy.Title(noteOrderBy.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Color",
                selected = noteOrderBy is NoteOrderBy.Color,
                onSelected = { onOrderChange(NoteOrderBy.Title(noteOrderBy.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            DefaultRadioButton(text = "Ascending",
                selected = noteOrderBy.orderType is OrderType.Ascending,
                onSelected = { onOrderChange(noteOrderBy.copy(OrderType.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Descending",
                selected = noteOrderBy.orderType is OrderType.Descending,
                onSelected = { onOrderChange(noteOrderBy.copy(OrderType.Descending)) }
            )

        }
    }

}