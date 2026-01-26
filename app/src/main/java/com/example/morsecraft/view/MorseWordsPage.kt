package com.example.morsecraft.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chaquo.python.Python
import com.example.morsecraft.utils.BackButton
import com.example.morsecraft.utils.CheckButton
import com.example.morsecraft.utils.DeleteButton
import com.example.morsecraft.utils.MainTitle
import com.example.morsecraft.utils.MediumTitle
import com.example.morsecraft.utils.MorseDashButton
import com.example.morsecraft.utils.MorseDotButton
import com.example.morsecraft.utils.QuestionTable
import com.example.morsecraft.utils.ResultBadge
import com.example.morsecraft.utils.SmallTitle
import com.example.morsecraft.utils.SmallTouchTitle
import com.example.morsecraft.utils.SpaceButton
import com.example.morsecraft.utils.SubMainTitle
import com.example.morsecraft.view_model.CheckResult


@Composable
fun MorseWordsPage(navController: NavController) {
    var morseText by remember {mutableStateOf("")}
    var currentWord by remember {mutableStateOf<String?>(null)}
    var result by remember {mutableStateOf<CheckResult?>(null)}
    var roundsCompleted by remember { mutableStateOf(0) }
    var roundsPassed by remember { mutableStateOf(0) }
    var showGameOver by remember { mutableStateOf(false) }
    var resultTitle by remember { mutableStateOf("") }

    val py = remember { Python.getInstance().getModule("model.morsecoder_words")}

    LaunchedEffect(Unit) {
        currentWord = py.callAttr("random_word").toString()
        roundsCompleted += 1
    }
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
                navController.popBackStack()
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MediumTitle("Round: $roundsCompleted/10")
            MainTitle("ENCODE GAME")
            MediumTitle("Level 2 - WORDS")
            Spacer(Modifier.height(25.dp))
            SubMainTitle("Translate into Morse Code:")
            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                QuestionTable(currentWord ?: "...")
                ResultBadge(
                    result = result,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                )
            }
            Spacer(Modifier.height(20.dp))
            TextField(
                value = morseText,
                onValueChange = { morseText = it },
                label = { SubMainTitle("Your Morse Code") },
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Spacer(Modifier.height(20.dp))
            Row {
                MorseDotButton({ morseText += "."})
                Spacer(Modifier.width(10.dp))
                MorseDashButton({ morseText += "_"})
            }
            Spacer(Modifier.height(10.dp))
            SpaceButton { morseText += " " }
            Row{
                CheckButton { val word = currentWord ?:return@CheckButton
                    val ok = py.callAttr("check_l2", word, morseText).toBoolean()
                    result = if (ok) CheckResult.OK else CheckResult.WRONG
                    morseText = ""
                    if (ok) {
                        roundsCompleted += 1
                        roundsPassed += 1
                        if (roundsCompleted >= 10) {
                            showGameOver = true
                            currentWord = null
                        } else {
                            currentWord = py.callAttr("random_word").toString()
                        }
                    }
                    else {
                        roundsCompleted += 1
                        roundsPassed += 0
                        if (roundsCompleted >= 10) {
                            showGameOver = true
                            currentWord = null
                        } else {
                            currentWord = py.callAttr("random_word").toString()
                        }
                    }
                }
                Spacer(Modifier.width(10.dp))
                DeleteButton { morseText = "" }
            }

            LaunchedEffect(result) {
                if (result != null) {
                    kotlinx.coroutines.delay(1200)
                    result = null
                }
            }
            if (roundsPassed<2){
                resultTitle = "Noob!"
            }
            if ((roundsPassed>2) && (roundsPassed<4)){
                resultTitle = "Could be worse!"
            }
            if ((roundsPassed>4) && (roundsPassed>6)){
                resultTitle = "Nice one!"
            }
            if ((roundsPassed>6) && (roundsPassed>8)){
                resultTitle = "Very good!"
            }
            if (roundsPassed>8){
                resultTitle = "You're expert!"
            }
            if (showGameOver) {
                AlertDialog(
                    onDismissRequest = { },
                    title = { MainTitle(resultTitle) },
                    text = { SmallTitle("You've been passed $roundsPassed of 10 rounds") },
                    confirmButton = {
                        TextButton(onClick = { showGameOver = false
                            navController.popBackStack()}) {
                            SmallTouchTitle("Back to atrium")
                        }
                    }
                )
            }
        }

    }
}


