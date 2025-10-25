package com.example.morsecraft.view
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.morsecraft.utils.MainTitle
import com.example.morsecraft.utils.WarningTitleBig
import com.example.morsecraft.utils.routes

@Composable
fun DisclaimerPage(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MainTitle("MorseCraft v0.1")
        WarningTitleBig("Warning!")
        Text(
            text = "This application is under active development! Beware of bugs!"
        )
        Button(onClick = {
            navController.navigate(routes.mainmenupage)
        }) {
            Text(
                text = "I understand"
            )
        }
    }
}