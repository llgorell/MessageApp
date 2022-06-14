package com.example.messageappebcom.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = latablue_light,
    primaryVariant = lateblue_dark,
    background = gray_background,
    onPrimary = Color.White,
    onBackground = gray_dark_background
)

private val LightColorPalette = lightColors(
    primary = latablue_light,
    primaryVariant = lateblue_dark,
    background = gray_background,
    onPrimary = Color.Black,
    onBackground = gray_dark_background

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MessageAppEbcomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}