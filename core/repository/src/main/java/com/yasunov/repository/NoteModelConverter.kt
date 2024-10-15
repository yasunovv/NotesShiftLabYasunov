package com.yasunov.repository

import com.yasunov.database.entity.NoteEntity
import com.yasunov.repository.model.NoteModel

internal fun noteModelToNoteEntity(noteModel: NoteModel): NoteEntity {
    return NoteEntity(
        id = null,
        title = noteModel.title,
        body = noteModel.body,
        dateOfCreation = noteModel.dateOfCreation
    )
}

internal fun noteEntityToNoteModel(noteModel: NoteEntity): NoteModel {
    return NoteModel(
        id = noteModel.id,
        title = noteModel.title,
        body = noteModel.body,
        dateOfCreation = noteModel.dateOfCreation
    )
}