package com.example.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core_designsystem.color.ColorTokens
import com.example.core_designsystem.typography.AppTypograophy
import com.example.core_designsystem.typography.TypographyTokens

@Composable
fun SectionTitle(title: String,modifier:Modifier = Modifier)
{
    Text(style = AppTypograophy.typography.bodyMedium,text = title,
        color = Color.Black,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
}