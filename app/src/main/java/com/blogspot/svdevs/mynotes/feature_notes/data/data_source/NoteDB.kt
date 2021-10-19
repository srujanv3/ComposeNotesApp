package com.blogspot.svdevs.mynotes.feature_notes.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blogspot.svdevs.mynotes.feature_notes.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDB: RoomDatabase() {

    abstract val noteDao:NoteDao

    companion object {

        const val DATABASE_NAME = "notes_db"
    }
}