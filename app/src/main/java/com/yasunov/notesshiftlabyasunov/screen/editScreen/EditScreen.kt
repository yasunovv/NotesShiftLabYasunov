package com.yasunov.notesshiftlabyasunov.screen.editScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.yasunov.designsystem.component.ShiftButton
import com.yasunov.designsystem.component.ShiftScaffold
import com.yasunov.designsystem.component.ShiftTextField
import com.yasunov.designsystem.theme.NotesShiftAppYasunov
import com.yasunov.designsystem.theme.Typography
import com.yasunov.designsystem.R as RDesignSystem

@Composable
fun EditScreen(
    onBackClick: () -> Unit,
    noteAddedListener: () -> Unit,
    id: Int,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<EditScreenViewModel, EditScreenViewModel.Factory>(
        creationCallback = { factory -> factory.create(id = id) }
    )
    LaunchedEffect(viewModel) {
        viewModel.loadNote()
    }
    val uiState by viewModel.uiState.collectAsState()
    val dp0 = dimensionResource(id = RDesignSystem.dimen._0dp)
    val dp4 = dimensionResource(id = RDesignSystem.dimen._4dp)
    val dp8 = dimensionResource(id = RDesignSystem.dimen._8dp)
    val dp12 = dimensionResource(id = RDesignSystem.dimen._12dp)
    val dp16 = dimensionResource(id = RDesignSystem.dimen._16dp)
    ShiftScaffold(
        topBar = {
            TopAppBar(
                title = {
                    val addNoteStringResource =
                        stringResource(com.yasunov.notesshiftlabyasunov.R.string.add_note)
                    Text(text = addNoteStringResource, style = Typography.body2, color = Color.Gray)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClick()
                        viewModel.addNote()
                    }) {
                        val contentDescription =
                            stringResource(com.yasunov.notesshiftlabyasunov.R.string.back)
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = contentDescription
                        )
                    }
                },
                actions = {
                    Spacer(modifier = Modifier.width(dp16))
                    ShiftButton(
                        onClick = {
                            viewModel.onSaveButtonClicked()
                            if (uiState.title.isNotEmpty()) {
                                noteAddedListener()
                            }
                        }, contentPadding = PaddingValues(
                            horizontal = dp12,
                            vertical = dp4
                        )
                    ) {
                        val saveStringResource =
                            stringResource(com.yasunov.notesshiftlabyasunov.R.string.save)
                        Text(
                            text = saveStringResource,
                            style = Typography.body1,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(dp16))
                },
                backgroundColor = NotesShiftAppYasunov.colors.uiBackground,
                contentColor = NotesShiftAppYasunov.colors.brand,
                elevation = dp0,
                modifier = Modifier
                    .padding(top = dp16)
                    .then(modifier)
            )
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dp16),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            val title = stringResource(com.yasunov.notesshiftlabyasunov.R.string.title)
            ShiftTextField(
                value = uiState.title,
                onValueChange = { viewModel.updateTitle(it) },
                keyboardType = KeyboardType.Text,
                decorationBox = { innerTextField ->
                    if (uiState.title.isEmpty()) {
                        Text(text = title, style = Typography.h5, color = Color.Gray)
                    }
                    innerTextField()
                },
                textStyle = Typography.h5.copy(color = Color.Black),
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(dp12))
            val addNote = stringResource(com.yasunov.notesshiftlabyasunov.R.string.add_note)
            ShiftTextField(
                value = uiState.text,
                onValueChange = { viewModel.updateText(it) },
                keyboardType = KeyboardType.Unspecified,
                decorationBox = { innerTextField ->
                    if (uiState.text.isEmpty()) {
                        Text(text = addNote, style = Typography.body1, color = Color.Gray)
                    }
                    innerTextField()
                },
                maxLines = 999,
                textStyle = Typography.body1.copy(color = Color.Black),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = dp8),
            )
        }
    }

}
