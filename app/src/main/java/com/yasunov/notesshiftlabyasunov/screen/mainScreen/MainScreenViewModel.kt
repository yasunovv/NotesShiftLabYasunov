package com.yasunov.notesshiftlabyasunov.screen.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasunov.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: NoteRepository,
) : ViewModel() {
    private var _uiState: MutableStateFlow<MainScreenUiState> =
        MutableStateFlow(MainScreenUiState.Loading)
    val uiState: StateFlow<MainScreenUiState> get() = _uiState
    fun loadNotes() {
        viewModelScope.launch {
            repository.getAllNotesByDate().collect { result ->
                _uiState.update {
                    MainScreenUiState.Success(list = result.toImmutableList())
                }
            }
        }
    }


}

