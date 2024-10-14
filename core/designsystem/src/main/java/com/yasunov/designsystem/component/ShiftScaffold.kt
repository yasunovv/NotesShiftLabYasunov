package com.yasunov.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yasunov.designsystem.theme.NotesShiftAppYasunov

@Composable
fun ShiftScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,

    ) {
    Scaffold(
        modifier = modifier.consumeWindowInsets(WindowInsets.safeContent),
        scaffoldState = rememberScaffoldState(),
        topBar = topBar,
        drawerBackgroundColor = NotesShiftAppYasunov.colors.uiBackground,
        drawerContentColor = NotesShiftAppYasunov.colors.uiBackground,
        drawerScrimColor = NotesShiftAppYasunov.colors.uiBackground,
        backgroundColor = NotesShiftAppYasunov.colors.uiBackground,
        contentColor = NotesShiftAppYasunov.colors.uiBackground,
        bottomBar = bottomBar,
    ) { padding ->
        content(padding)

    }
}