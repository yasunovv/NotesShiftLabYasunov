package com.yasunov.notesshiftlabyasunov.screen.mainScreen

import androidx.compose.runtime.Immutable
import com.yasunov.repository.model.NoteModel
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed class MainScreenUiState {
    data object NoNotes : MainScreenUiState()
    data object Loading : MainScreenUiState()
    data class Success(
        val list: ImmutableList<NoteModel>,
        val selectedNote: Int? = null,
    ) : MainScreenUiState()

}