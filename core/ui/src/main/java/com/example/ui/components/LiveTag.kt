package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LiveTag()
{
    Box(modifier = Modifier.background(color = Color.Red,
                  shape = MaterialTheme.shapes.small).padding(horizontal = 6.dp,vertical = 2.dp))
    {
        Text(
            text = "LIVE",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White
        )
    }
}