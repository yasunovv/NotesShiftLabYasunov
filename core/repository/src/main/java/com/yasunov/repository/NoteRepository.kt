package com.yasunov.repository

import com.yasunov.repository.model.NoteModel
import kotlinx.coroutines.flow.Flow


interface NoteRepository {
    suspend fun insertNote(noteModel: NoteModel)
    suspend fun updateNote(noteModel: NoteModel)
    fun getAllNotesByDate(): Flow<List<NoteModel>>
    suspend fun deleteById(id: Int)
}