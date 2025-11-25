package com.example.core_designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import com.example.core_designsystem.color.ColorTokens

val DarkThemeScheme: ColorScheme = darkColorScheme(
    primary = ColorTokens.yellow,
    onPrimary = ColorTokens.Background,
    background = ColorTokens.Background,
    onBackground = ColorTokens.TextPrimary,

    surface = ColorTokens.Surface,
    onSurface = ColorTokens.TextPrimary,
    secondary = ColorTokens.TextSecondary,
    onSecondary = ColorTokens.TextPrimary,

    outline = ColorTokens.TextSecondary,

    surfaceVariant = ColorTokens.Surface,
    onSurfaceVariant = ColorTokens.TextSecondary,

    tertiary = ColorTokens.yellow,
    onTertiary = ColorTokens.Background
)