package com.yasunov.repository.di

import com.yasunov.repository.NoteRepository
import com.yasunov.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBindModule {
    @Binds
    @Singleton
    fun bindRepository(repository: NoteRepositoryImpl): NoteRepository
}
