package com.blogspot.svdevs.mynotes.feature_notes.domain.use_case

import com.blogspot.svdevs.mynotes.feature_notes.domain.model.Note
import com.blogspot.svdevs.mynotes.feature_notes.domain.repository.NoteRepository
import com.blogspot.svdevs.mynotes.feature_notes.domain.utils.NoteOrderBy
import com.blogspot.svdevs.mynotes.feature_notes.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllNotesUseCase(private val repository: NoteRepository) {

    operator fun invoke(
        noteOrderBy: NoteOrderBy = NoteOrderBy.Date(OrderType.Descending) // Default notes order
    ): Flow<List<Note>>{
        return repository.getAllNotes().map { notes ->
            // mapping all notes to a new list to apply the order type on the notes
            when(noteOrderBy.orderType)  {
                is OrderType.Ascending -> {
                    when(noteOrderBy){
                        is NoteOrderBy.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrderBy.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrderBy.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrderBy){
                        is NoteOrderBy.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrderBy.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrderBy.Color -> notes.sortedByDescending { it.color }
                    }
                }
        }
        }

    }

}