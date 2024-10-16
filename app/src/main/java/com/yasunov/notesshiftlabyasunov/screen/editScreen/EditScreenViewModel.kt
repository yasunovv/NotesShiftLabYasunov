package com.yasunov.notesshiftlabyasunov.screen.editScreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasunov.notesshiftlabyasunov.R
import com.yasunov.repository.NoteRepository
import com.yasunov.repository.model.NoteModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = EditScreenViewModel.Factory::class)
class EditScreenViewModel @AssistedInject constructor(
    private val repository: NoteRepository,
    @ApplicationContext private val context: Context,
    @Assisted private val id: Int,
) : ViewModel() {
    private var _uiState: MutableStateFlow<EditScreenUiState> =
        MutableStateFlow(EditScreenUiState(title = "", text = ""))
    val uiState: StateFlow<EditScreenUiState> get() = _uiState
    fun updateText(text: String) {
        _uiState.update {
            it.copy(text = text)
        }
    }

    fun updateTitle(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    fun onSaveButtonClicked() {
        val toast = Toast(context)
        if (uiState.value.title.isEmpty()) {
            toast.apply {
                setText(context.getString(R.string.please_add_title))
                show()
            }
            return
        }
        addNote()
        toast.apply {
            setText(context.getString(R.string.successful))
            show()
        }


    }

    internal fun addNote() {
        if (uiState.value.title.isEmpty()) return
        viewModelScope.launch {
            repository.insertNote(
                noteModel = NoteModel(
                    id = if (id != -1) id else null,
                    title = uiState.value.title,
                    body = uiState.value.text,
                    dateOfCreation = System.currentTimeMillis(),
                )
            )
        }
    }

    fun loadNote() {
        if (id == -1) return
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getNoteById(id = id)
            _uiState.update {
                EditScreenUiState(title = result.title, text = result.body)
            }
        }


    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int): EditScreenViewModel
    }


}