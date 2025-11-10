package com.example.morsecraft.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.morsecraft.utils.BackButton
import com.example.morsecraft.utils.DecodeButton
import com.example.morsecraft.utils.MainTitle
import com.example.morsecraft.utils.MicroLogo
import com.example.morsecraft.utils.PlayButton
import com.example.morsecraft.utils.RecButton
import com.example.morsecraft.utils.StopButton
import com.example.morsecraft.utils.SubMainTitle
import com.example.morsecraft.utils.routes

@Composable
fun DecodePage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            BackButton {
                navController.navigate(routes.mainmenupage)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MainTitle("DECODE WITH")
            MainTitle("MICROPHONE")
            Spacer(Modifier.height(45.dp))
            Row {
                MicroLogo()
                Column {
                    SubMainTitle("Use device microphone to")
                    SubMainTitle("transmit your message")
                    SubMainTitle("in MORSE CODE!")
                }
            }

            Spacer(Modifier.height(20.dp))

            Row {
                RecButton { }
                Spacer(Modifier.width(20.dp))
                PlayButton { }
                Spacer(Modifier.width(20.dp))
                StopButton { }
            }

            Spacer(Modifier.height(20.dp))
            DecodeButton { }

            Spacer(Modifier.height(20.dp))
            TextField(
                value = "",
                onValueChange = {},
                label = { SubMainTitle("Morse code message:") },
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Spacer(Modifier.height(20.dp))
            TextField(
                value = "",
                onValueChange = {},
                label = { SubMainTitle("Decoded message:") },
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }
    }



}
