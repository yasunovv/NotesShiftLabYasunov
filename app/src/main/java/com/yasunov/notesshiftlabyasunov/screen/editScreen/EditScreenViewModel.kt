package com.yasunov.notesshiftlabyasunov.screen.editScreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasunov.notesshiftlabyasunov.R
import com.yasunov.repository.NoteRepository
import com.yasunov.repository.model.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel @Inject constructor(
    private val repository: NoteRepository,
    @ApplicationContext private val context: Context,
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
        viewModelScope.launch {
            repository.insertNote(
                noteModel = NoteModel(
                    id = -1,
                    title = uiState.value.title,
                    body = uiState.value.text,
                    dateOfCreation = System.currentTimeMillis(),
                )
            )
        }
        toast.apply {
            setText(context.getString(R.string.successful))
            show()
        }


    }

}