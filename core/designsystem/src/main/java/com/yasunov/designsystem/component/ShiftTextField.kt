package com.yasunov.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.yasunov.designsystem.R
import com.yasunov.designsystem.theme.NotesShiftAppYasunov
import com.yasunov.designsystem.theme.Typography

@Composable
fun ShiftTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier
) {
    val dp1 = dimensionResource(id = R.dimen._1dp)
    val dp16 = dimensionResource(id = R.dimen._16dp)
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        maxLines = 1,
        cursorBrush = SolidColor(NotesShiftAppYasunov.colors.brand),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        textStyle = Typography.body1,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = dp1,
                color = NotesShiftAppYasunov.colors.light,
                shape = RoundedCornerShape(size = dp16)
            )
            .background(color = Color.White, shape = RoundedCornerShape(size = dp16))
            .padding(all = dp16),
    )

}
