package com.yasunov.repository

import com.yasunov.database.dao.NoteDao
import com.yasunov.repository.model.NoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
) : NoteRepository {
    override suspend fun insertNote(noteModel: NoteModel) =
        noteDao.insertNote(note = noteModelToNoteEntity(noteModel = noteModel))

    override suspend fun updateNote(noteModel: NoteModel) =
        noteDao.updateNote(note = noteModelToNoteEntity(noteModel = noteModel))

    override fun getAllNotesByDate(): Flow<List<NoteModel>> =
        noteDao.getAllNotesByDate().map { it -> it.map { noteEntityToNoteModel(it) } }

    override suspend fun deleteById(id: Int) = noteDao.deleteById(id = id)
}