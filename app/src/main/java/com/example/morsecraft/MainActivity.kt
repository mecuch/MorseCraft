package com.example.morsecraft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.example.morsecraft.utils.routes
import com.example.morsecraft.view.DisclaimerPage
import com.example.morsecraft.view.MainMenuPage
import com.example.morsecraft.view.MorsePage


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = routes.disclaimerpage,
                builder = {
                    composable(routes.disclaimerpage) {
                    DisclaimerPage(navController = navController)
                }
                    composable(routes.mainmenupage) {
                    MainMenuPage(navController = navController)
                }
                    composable(routes.morsepage) {
                        MorsePage()
                    }
            }
            )
        }
    }
}