package com.yasunov.database.di

import android.content.Context
import com.yasunov.database.NoteDatabase
import com.yasunov.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase =
        NoteDatabase.getDatabase(context = context)


    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao()
    }
}
