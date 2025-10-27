package com.example.morsecraft.view

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.morsecraft.utils.MainTitle
import com.example.morsecraft.utils.MenuNormalTouchButton
import com.example.morsecraft.utils.routes

@Composable
fun MainMenuPage(navController: NavController){
    val activity = (LocalContext.current as? Activity)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        MainTitle("MorseCraft v0.1")
        Spacer(Modifier.height(20.dp))
        MenuNormalTouchButton(
            onClick = {
            navController.navigate(routes.morsepage)
        },
            text = "Encode Game")
        Spacer(Modifier.height(20.dp))
        MenuNormalTouchButton(
            onClick = {},
            text = "Decode with microphone")
        Spacer(Modifier.height(20.dp))
        MenuNormalTouchButton(
            onClick = {activity?.finishAffinity()},
            text = "Exit")

    }

}