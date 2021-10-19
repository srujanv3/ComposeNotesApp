package com.blogspot.svdevs.mynotes.feature_notes.presentation.notes


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blogspot.svdevs.mynotes.feature_notes.domain.model.Note
import com.blogspot.svdevs.mynotes.feature_notes.domain.use_case.NoteUseCase
import com.blogspot.svdevs.mynotes.feature_notes.domain.utils.NoteOrderBy
import com.blogspot.svdevs.mynotes.feature_notes.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var recentlyDeletedNote: Note? = null

    //this variable will keep a track of the coroutine calls made
    private var getNotesJob: Job? = null

    init {
        getOrder(NoteOrderBy.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            // handles the event. Functionality for the user actions
            is NotesEvent.Order -> {
                if (state.value.noteOrderBy::class == event.noteOrderBy::class &&
                    state.value.noteOrderBy.orderType == event.noteOrderBy.orderType
                ) {
                    return
                }
                getOrder(event.noteOrderBy)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCase.deleteNoteUseCase(event.note)
                    recentlyDeletedNote =
                        event.note // keeping a track of the latest deleted note for restoring
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCase.addNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

        }
    }

    private fun getOrder(noteOrderBy: NoteOrderBy) {
        getNotesJob?.cancel()

        getNotesJob = noteUseCase.getAllNotesUseCase(noteOrderBy)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrderBy = noteOrderBy
                )
            }
            .launchIn(viewModelScope)

    }

}