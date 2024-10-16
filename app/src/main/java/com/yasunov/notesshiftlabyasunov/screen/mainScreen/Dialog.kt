package com.yasunov.notesshiftlabyasunov.screen.mainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.yasunov.designsystem.R
import com.yasunov.designsystem.component.ShiftButton
import com.yasunov.designsystem.theme.Typography
import androidx.compose.material.AlertDialog as MaterialAlertDialog

@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    textButton: Pair<String, String>,
    modifier: Modifier = Modifier
) {
    val dp4 = dimensionResource(id = R.dimen._4dp)
    val dp8 = dimensionResource(id = R.dimen._8dp)
    val dp24 = dimensionResource(id = R.dimen._24dp)
    val dp32 = dimensionResource(id = R.dimen._32dp)
    val dp16 = dimensionResource(id = R.dimen._16dp)
    MaterialAlertDialog(
        onDismissRequest = onDismissRequest,
        buttons = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(bottom = dp8)
                    .fillMaxWidth()
            ) {
                val contentPadding = PaddingValues(horizontal = dp24, vertical = dp4)
                ShiftButton(
                    contentPadding = contentPadding,
                    onClick = onDismissRequest
                ) {
                    Text(
                        text = textButton.first,
                        style = Typography.body1,
                        color = Color.White,
                    )
                }
                Spacer(
                    modifier = Modifier
                        .width(dp32)
                )
                ShiftButton(
                    contentPadding = contentPadding,
                    onClick = onConfirmation
                ) {
                    Text(
                        text = textButton.second,
                        style = Typography.body1,
                        color = Color.White
                    )
                }

            }
        },
        title = {
            Text(text = dialogTitle, style = Typography.h6, color = Color.Black)
        },
        text = {
            Text(text = dialogText, style = Typography.body2, color = Color.Black)
        },
        shape = RoundedCornerShape(dp16),
        backgroundColor = Color.White,
        contentColor = Color.White,
        modifier = modifier,
    )
}