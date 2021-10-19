package com.blogspot.svdevs.mynotes.feature_notes.presentation.notes

import com.blogspot.svdevs.mynotes.feature_notes.domain.model.Note
import com.blogspot.svdevs.mynotes.feature_notes.domain.utils.NoteOrderBy

sealed class NotesEvent{
    // this class will contain all the actions that the user can perform on the notes screen

    data class Order(val noteOrderBy: NoteOrderBy): NotesEvent() // when user changes the order of notes
    data class DeleteNote(val note: Note): NotesEvent() // when user deletes a note
    object RestoreNote : NotesEvent() // when user hits the undo option on snackbar
    object ToggleOrderSection: NotesEvent() // when user toggles the order notes section
}
