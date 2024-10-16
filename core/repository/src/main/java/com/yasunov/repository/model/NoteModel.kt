package com.yasunov.repository.model

data class NoteModel(
    val id: Int?,
    val title: String,
    val body: String,
    val dateOfCreation: Long,
)