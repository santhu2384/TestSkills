package com.example.testskills.presentation

import com.example.feature_home.HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.core_designsystem.theme.AppTheme
import com.example.testskills.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DaznMainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //HomeScreen()
            AppTheme {
                AppNavGraph()
            }

        }
    }
}