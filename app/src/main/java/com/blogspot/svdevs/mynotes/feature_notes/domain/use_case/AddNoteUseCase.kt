package com.blogspot.svdevs.mynotes.feature_notes.domain.use_case

import com.blogspot.svdevs.mynotes.feature_notes.domain.model.InvalidNoteHandling
import com.blogspot.svdevs.mynotes.feature_notes.domain.model.Note
import com.blogspot.svdevs.mynotes.feature_notes.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteHandling::class)
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
            throw InvalidNoteHandling("Title for the note can't be empty")
        }
        if(note.content.isBlank()){
            throw InvalidNoteHandling("Content of the note can't be empty")
        }
        repository.insertNote(note)
    }
}