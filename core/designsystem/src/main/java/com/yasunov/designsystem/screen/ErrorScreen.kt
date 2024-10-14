package com.yasunov.designsystem.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.yasunov.designsystem.R
import com.yasunov.designsystem.component.ShiftButton
import com.yasunov.designsystem.theme.NotesShiftAppYasunov
import com.yasunov.designsystem.theme.Typography

@Composable
fun ErrorScreen(
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        val errorText = stringResource(R.string.error)
        Text(
            text = errorText,
            style = Typography.body1,
            color = NotesShiftAppYasunov.colors.titleText
        )
        val height = dimensionResource(id = R.dimen._8dp)
        Spacer(modifier = modifier.height(height))
        ShiftButton(onClick = onClickButton) {
            val repeat = stringResource(R.string.repeat)
            Text(
                text = repeat,
                style = Typography.body1,
                color = Color.White
            )
        }
    }
}
