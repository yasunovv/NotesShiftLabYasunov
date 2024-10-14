package com.yasunov.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.yasunov.designsystem.R
import com.yasunov.designsystem.theme.NotesShiftAppYasunov
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography

@Composable
fun ShiftButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,

    content: @Composable RowScope.() -> Unit,
) {
    val dp32 = dimensionResource(id = R.dimen._32dp)
    val dp16 = dimensionResource(id = R.dimen._16dp)
    val contentPadding = PaddingValues(horizontal = dp32, vertical = dp16)
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
    ShiftAppInternTheme {
        ShiftButton(onClick = {}) {
            Text("Оформить заказ", style = Typography.body1)
        }
    }
}
