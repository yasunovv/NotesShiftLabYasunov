package com.yasunov.repository

import com.yasunov.database.entity.NoteEntity
import com.yasunov.repository.model.NoteModel

internal fun noteModelToNoteEntity(noteModel: NoteModel): NoteEntity {
    return NoteEntity(
        id = noteModel.id,
        title = noteModel.title,
        body = noteModel.body,
        dateOfCreation = noteModel.dateOfCreation
    )
}

internal fun noteEntityToNoteModel(noteEntity: NoteEntity): NoteModel {
    return NoteModel(
        id = noteEntity.id!!,
        title = noteEntity.title,
        body = noteEntity.body,
        dateOfCreation = noteEntity.dateOfCreation
    )
}