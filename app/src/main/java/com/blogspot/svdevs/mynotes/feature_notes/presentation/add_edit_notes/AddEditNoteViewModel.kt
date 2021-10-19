package com.blogspot.svdevs.mynotes.feature_notes.presentation.add_edit_notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blogspot.svdevs.mynotes.feature_notes.domain.model.InvalidNoteHandling
import com.blogspot.svdevs.mynotes.feature_notes.domain.model.Note
import com.blogspot.svdevs.mynotes.feature_notes.domain.use_case.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _noteTitle = mutableStateOf(NoteTextFieldState(hint = "Title"))
    val noteTile:State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(hint = "Description"))
    val noteContent:State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor:State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1){
                viewModelScope.launch {
                    noteUseCase.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTile.value.copy(
                            text = note.title,
                            isHintVis = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVis = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTile.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTile.value.copy(
                    isHintVis = !event.focus.isFocused &&
                            noteTile.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVis = !event.focus.isFocused &&
                            noteContent.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AddEditNoteEvent.SaveNote -> {
                // Adding a new note into the database
                viewModelScope.launch {
                    try {
                        noteUseCase.addNoteUseCase(
                            Note(
                                title = noteTile.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    }catch (e: InvalidNoteHandling){
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                msg = e.message ?: "Unknown Error occurred"
                            )
                        )

                    }
                }
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackBar(val msg: String): UiEvent()
        object SaveNote : UiEvent()
    }

}