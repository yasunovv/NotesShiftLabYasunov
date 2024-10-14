package com.yasunov.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColorPalette = NotesShiftAppYasunovColors(
    brand = Orange,
    uiBackground = White,
    iconPrimary = Orange,
    iconSecondary = IconSecondary,
    tertiaryText = TertiaryText,
    titleText = TitleText,
    bodyPrimaryText = BodyPrimaryText,
    appBarText = PrimaryText,
    secondaryText = SecondaryText,
    quarterlyText = QuarterlyText,
    light = Light,
    secondary = Secondary,
    extraLight = ExtraLight,
    yellow = Yellow,
    green = Green,
    red = Red
)

private val DarkColorPalette = LightColorPalette.copy()

@Composable
fun ProvideNotesShiftAppYasunovColors(
    colors: NotesShiftAppYasunovColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalNotesShiftAppYasunovColors provides colors, content = content)
}

@Composable
fun ShiftAppInternTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    ProvideNotesShiftAppYasunovColors(colors) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object NotesShiftAppYasunov {
    val colors: NotesShiftAppYasunovColors
        @Composable
        get() = LocalNotesShiftAppYasunovColors.current
}

@Immutable
data class NotesShiftAppYasunovColors(
    val brand: Color,
    val uiBackground: Color,
    val iconPrimary: Color = brand,
    val iconSecondary: Color,
    val tertiaryText: Color,
    val titleText: Color,
    val bodyPrimaryText: Color,
    val appBarText: Color,
    val secondaryText: Color,
    val quarterlyText: Color,
    val light: Color,
    val secondary: Color,
    val extraLight: Color,
    val yellow: Color,
    val green: Color,
    val red: Color
)


private val LocalNotesShiftAppYasunovColors = staticCompositionLocalOf<NotesShiftAppYasunovColors> {
    error("No colorPalette provided")
}

fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)