package com.example.morsecraft.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chaquo.python.Python
import com.example.morsecraft.utils.BackButton
import com.example.morsecraft.utils.ClickableLetterTable
import com.example.morsecraft.utils.MainTitle
import com.example.morsecraft.utils.MediumTitle
import com.example.morsecraft.utils.SmallTitle

@Composable
fun MorsePresenterPage(navController: NavController){
    var currentLetter by remember {mutableStateOf<String?>(null)}
    val letterRows = remember {
        ('A'..'Z').map { it.toString() }.chunked(7)
    }
    val py = remember { Python.getInstance().getModule("model.morsepresenter")}
    LaunchedEffect(Unit) {
        currentLetter = "<Pick letter\nto reveal code>"
    }


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
                navController.popBackStack()
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MainTitle("MORSE CODE")
            SmallTitle("Click on letter to see the code")
            Spacer(Modifier.height(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                letterRows.forEach { rowLetters ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        rowLetters.forEach { letter ->
                            ClickableLetterTable(
                                value = letter,
                                onClick = {
                                    currentLetter = py.callAttr("present", letter).toString()
                                }
                            )
                        }
                    }
                }
                MediumTitle("Code: $currentLetter")
            }
        }
    }
}
