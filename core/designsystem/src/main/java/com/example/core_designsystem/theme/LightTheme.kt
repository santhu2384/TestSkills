package com.example.core_designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import com.example.core_designsystem.color.ColorTokens

val LightThemeColors: ColorScheme = lightColorScheme(
    primary = ColorTokens.yellow,
    onPrimary = ColorTokens.Background,
    background = ColorTokens.Background,
    onBackground = ColorTokens.TextPrimary,
    surface = ColorTokens.Surface,
    onSurface = ColorTokens.TextPrimary,
    secondary = ColorTokens.TextSecondary,
    onSecondary = ColorTokens.TextPrimary,
    outline = ColorTokens.TextSecondary,
    surfaceVariant =  ColorTokens.Surface,
    onSurfaceVariant = ColorTokens.TextSecondary,
    // Top bar & bottom bar colors
    tertiary = ColorTokens.yellow,
    onTertiary = ColorTokens.Background
)