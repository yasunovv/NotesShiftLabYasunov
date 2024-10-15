package com.yasunov.repository

import android.util.Log
import com.yasunov.database.dao.NoteDao
import com.yasunov.repository.model.NoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl(
    @Inject private val noteDao: NoteDao,
) : NoteRepository {
    init {
        Log.e("NoteRepository", "HELLO")
    }
    override suspend fun insertNote(noteModel: NoteModel) =
        noteDao.insertNote(note = noteModelToNoteEntity(noteModel = noteModel))

    override suspend fun updateNote(noteModel: NoteModel) =
        noteDao.updateNote(note = noteModelToNoteEntity(noteModel = noteModel))

    override fun getAllNotesByDate(): Flow<List<NoteModel>> =
        noteDao.getAllNotesByDate().transform { it.map { ::noteEntityToNoteModel } }

    override suspend fun deleteById(id: Int) = noteDao.deleteById(id = id)
}