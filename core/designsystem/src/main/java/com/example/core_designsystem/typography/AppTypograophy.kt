package com.example.core_designsystem.typography

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


object AppTypograophy
{
    val typography = Typography(
     headlineLarge = TextStyle(fontSize = 28.sp,
        fontWeight = FontWeight.Bold),
        titleMedium = TextStyle(fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold),
        bodyMedium =  TextStyle(fontSize = 16.sp,
            fontWeight = FontWeight.Normal)
    )
}