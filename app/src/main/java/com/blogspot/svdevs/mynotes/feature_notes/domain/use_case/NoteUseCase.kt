package com.blogspot.svdevs.mynotes.feature_notes.domain.use_case

data class NoteUseCase (
    // this class will hold all the use cases in the project. From here the use cases will be injected into the view model
    // so that the view model constructor will not be cluttered.
    val getAllNotesUseCase: GetAllNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val getNote: GetNoteUseCase
    )