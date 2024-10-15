package com.yasunov.repository.di

import com.yasunov.database.dao.NoteDao
import com.yasunov.repository.NoteRepository
import com.yasunov.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindModule {
    @Singleton
    @Provides
    fun provideNoteRepositoryImpl(noteDao: NoteDao) = NoteRepositoryImpl(noteDao = noteDao)

    @Binds
    abstract fun bindRepository(repository: NoteRepositoryImpl): NoteRepository
}

