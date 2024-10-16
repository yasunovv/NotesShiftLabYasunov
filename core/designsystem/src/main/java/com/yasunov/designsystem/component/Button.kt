package com.yasunov.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yasunov.designsystem.theme.NotesShiftAppYasunov
import com.yasunov.designsystem.theme.ShiftAppNotesTheme
import com.yasunov.designsystem.theme.Typography

@Composable
fun ShiftButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = NotesShiftAppYasunov.colors.brand,
            contentColor = NotesShiftAppYasunov.colors.uiBackground,
        ),
        contentPadding = contentPadding,
        content = content,
    )
}


@Preview
@Composable
private fun ShiftButtonPreview() {
    ShiftAppNotesTheme {
        ShiftButton(onClick = {}) {
            Text("Оформить заказ", style = Typography.body1)
        }
    }
}
