package com.blogspot.svdevs.mynotes.feature_notes.presentation.add_edit_notes

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVis: Boolean = true
)