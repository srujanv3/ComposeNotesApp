package com.blogspot.svdevs.mynotes.di

import android.app.Application
import androidx.room.Room
import com.blogspot.svdevs.mynotes.feature_notes.data.data_source.NoteDB
import com.blogspot.svdevs.mynotes.feature_notes.data.data_source.NoteDao
import com.blogspot.svdevs.mynotes.feature_notes.data.repository.NoteRepositoryImpl
import com.blogspot.svdevs.mynotes.feature_notes.domain.repository.NoteRepository
import com.blogspot.svdevs.mynotes.feature_notes.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDB(app: Application): NoteDB{
        return Room.databaseBuilder(
            app,
            NoteDB::class.java,
            NoteDB.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDB): NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository): NoteUseCase{
        return NoteUseCase(
            getAllNotesUseCase = GetAllNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )
    }

}