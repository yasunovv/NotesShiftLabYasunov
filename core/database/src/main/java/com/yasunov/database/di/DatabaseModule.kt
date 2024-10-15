package com.yasunov.database.di

import android.content.Context
import com.yasunov.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(context: Context): NoteDatabase =
        NoteDatabase.getDatabase(context = context)
}
