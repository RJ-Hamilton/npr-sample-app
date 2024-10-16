package com.hamilton.nprsampleapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = LightBlue,
    inversePrimary = DarkBlue,
    background = LightGray,
    onPrimary = Black,
    surface = White,
    onSurface = Black,
    error = Red,
    onError = White
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlue,
    inversePrimary = LightBlue,
    background = DarkGray,
    onPrimary = Black,
    surface = Gray,
    onSurface = White,
    error = Red,
    onError = White
)

/**
 * Composable function to apply a custom theme for the CodingExercise app.
 *
 * This function applies a `MaterialTheme` to the content passed to it. The theme can dynamically
 * switch between a light or dark color scheme depending on the `darkTheme` parameter. The color
 * scheme and typography are customized based on the app's design requirements.
 *
 * @param darkTheme Boolean indicating whether to use the dark theme. Defaults to the system-wide
 * dark theme setting (`isSystemInDarkTheme()`).
 * @param content A `@Composable` lambda that represents the UI content to which the theme will be
 * applied.
 *
 * Example usage:
 * ```
 * NPRSampleAppTheme {
 *     // Your UI content here
 *     MyComposableScreen()
 * }
 * ```
 */
@Composable
fun NPRSampleAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}