package com.yasunov.designsystem.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.yasunov.designsystem.R
import com.yasunov.designsystem.theme.NotesShiftAppYasunov


@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        val size = dimensionResource(id = R.dimen._64dp)
        CircularProgressIndicator(
            color = NotesShiftAppYasunov.colors.brand,
            modifier = modifier.size(size)
        )
    }
}