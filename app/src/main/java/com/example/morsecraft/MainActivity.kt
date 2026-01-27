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
import com.example.morsecraft.view.DecodePage
import com.example.morsecraft.view.DisclaimerPage
import com.example.morsecraft.view.MainMenuPage
import com.example.morsecraft.view.MorseAtriumPage
import com.example.morsecraft.view.MorsePage
import com.example.morsecraft.view.MorsePresenterPage
import com.example.morsecraft.view.MorseSentencePage
import com.example.morsecraft.view.MorseWordsPage
import com.example.morsecraft.view.TrainingAtriumPage
import com.example.morsecraft.view.TrainingPage
import com.example.morsecraft.view.TrainingSentencesPage
import com.example.morsecraft.view.TrainingWordsPage


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = routes.disclaimerpage) {
                composable(routes.disclaimerpage) { DisclaimerPage(navController) }
                composable(routes.mainmenupage) { MainMenuPage(navController) }
                composable(routes.morsepage) { MorsePage(navController) }
                composable(routes.morsewordpage) { MorseWordsPage(navController) }
                composable(routes.morsesentencepage) { MorseSentencePage(navController) }
                composable(routes.morseatriumpage) { MorseAtriumPage(navController) }
                composable(routes.decodepage) { DecodePage(navController) }
                composable(routes.trainatriumpage) { TrainingAtriumPage(navController) }
                composable(routes.trainingpage) { TrainingPage(navController) }
                composable(routes.trainingwordspage) { TrainingWordsPage(navController) }
                composable(routes.trainingsentencespage) { TrainingSentencesPage(navController) }
                composable(routes.presenterpage) { MorsePresenterPage(navController) }
            }
        }
    }
}