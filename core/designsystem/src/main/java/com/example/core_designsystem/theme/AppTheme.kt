package com.example.core_designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.core_designsystem.typography.AppTypograophy

@Composable
fun AppTheme(darktheme:Boolean = isSystemInDarkTheme(),content: @Composable () -> Unit )
{
    MaterialTheme(
        colorScheme = if(darktheme) DarkThemeScheme else LightThemeColors,
        typography = AppTypograophy.typography,
            content = content
    )

}