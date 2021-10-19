package com.blogspot.svdevs.mynotes.feature_notes.domain.repository

import com.blogspot.svdevs.mynotes.feature_notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    // this is an interface because we can easily create fakes of this repository for testing purposes

    fun getAllNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}