package com.example.myapplication.view

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.myapplication.utils.routes

@Composable
fun MainMenuPage(navController: NavController){
    val activity = (LocalContext.current as? Activity)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "MorseCraft POC Edition"
        )
        Button(onClick = {
            navController.navigate(routes.morsepage)
        }) {
            Text(
                text = "Start Demo"
            )
        }
        Button(onClick = {activity?.finishAffinity()}) {
            Text(
                text = "Exit"
            )
        }
    }

}