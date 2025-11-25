package com.example.testskills.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature_home.HomeScreen
import com.example.feature_player.PlayerScreen

object Routes {
    const val HOME = "home"
    const val PLAYER = "player/{videoid}"
}


@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController())
{
    NavHost(navController = navController, startDestination = "home")
    {
        composable(Routes.HOME)
        {
            HomeScreen(OnClickVideo = {
                videoid -> navController.navigate("player/$videoid")
            })
        }

        composable(Routes.PLAYER,)
        { backStackEntry ->
            val videoid = backStackEntry.arguments?.getString("videoid")?:""
            Log.d("Navigation", "Navigating to Player with id: $videoid")

            PlayerScreen(videoid = videoid)
        }
    }
}