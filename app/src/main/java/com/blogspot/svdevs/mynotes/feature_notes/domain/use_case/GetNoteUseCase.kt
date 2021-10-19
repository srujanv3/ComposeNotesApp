package com.blogspot.svdevs.mynotes.feature_notes.domain.use_case

import com.blogspot.svdevs.mynotes.feature_notes.domain.model.Note
import com.blogspot.svdevs.mynotes.feature_notes.domain.repository.NoteRepository

class GetNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(id: Int): Note?{
        return repository.getNoteById(id)
    }

}