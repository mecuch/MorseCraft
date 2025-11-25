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
import com.example.morsecraft.utils.Disclaimer
import com.example.morsecraft.utils.NormalTouchButton
import com.example.morsecraft.utils.routes

@Composable
fun DisclaimerPage(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Disclaimer()
        NormalTouchButton(
            onClick = {
                navController.navigate(routes.mainmenupage)
            },
            text = "I understand!"
        )
    }}