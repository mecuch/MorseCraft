package com.example.morsecraft.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.morsecraft.R
import com.example.morsecraft.utils.BackButton
import com.example.morsecraft.utils.BigTitleBlack
import com.example.morsecraft.utils.MainTitle
import com.example.morsecraft.utils.NormalTouchButton
import com.example.morsecraft.utils.SubMainTitle
import com.example.morsecraft.utils.routes

@Composable
fun MorseAtriumPage(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton {
                navController.navigate(routes.mainmenupage)
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.encod_menu),
                    contentDescription = "encode menu",
                    modifier = Modifier
                        .size(75.dp)
                )
                Spacer(Modifier.width(30.dp))
                MainTitle("ENCODE \nGAME")
            }
            Spacer(Modifier.height(25.dp))
            BigTitleBlack("Choose your level!")
            Spacer(Modifier.height(30.dp))
            NormalTouchButton(
                onClick = {
                    navController.navigate(routes.morsepage)
                },
                text = "EASY - letters"
            )
            Spacer(Modifier.height(30.dp))
            NormalTouchButton(
                onClick = {
                    navController.navigate(routes.morsewordpage)
                },
                text = "MEDIUM - words"
            )
            Spacer(Modifier.height(30.dp))
            NormalTouchButton(
                onClick = {
                    navController.navigate(routes.morsesentencepage)
                },
                text = "HARD - sentences"
            )

        }
    }
}
