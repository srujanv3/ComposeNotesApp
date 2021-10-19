package com.blogspot.svdevs.mynotes.feature_notes.presentation.notes

import com.blogspot.svdevs.mynotes.feature_notes.domain.model.Note
import com.blogspot.svdevs.mynotes.feature_notes.domain.utils.NoteOrderBy
import com.blogspot.svdevs.mynotes.feature_notes.domain.utils.OrderType

data class NoteState(
    //this class will contain the info od the note state ie their ordering, is sort bar expanded or collapsed

    val notes: List<Note> = emptyList(),
    val noteOrderBy: NoteOrderBy = NoteOrderBy.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false

)
