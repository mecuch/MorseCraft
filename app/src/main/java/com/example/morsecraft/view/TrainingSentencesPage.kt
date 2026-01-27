package com.example.morsecraft.view

import com.example.morsecraft.utils.NormalTouchButton
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chaquo.python.Python
import com.example.morsecraft.utils.BackButton
import com.example.morsecraft.utils.CheckButton
import com.example.morsecraft.utils.DeleteButton
import com.example.morsecraft.utils.MainTitle
import com.example.morsecraft.utils.MorseDashButton
import com.example.morsecraft.utils.MorseDotButton
import com.example.morsecraft.utils.QuestionTable
import com.example.morsecraft.utils.ResultBadge
import com.example.morsecraft.utils.SpaceButton
import com.example.morsecraft.utils.SubMainTitle
import com.example.morsecraft.view_model.CheckResult


@Composable
fun TrainingSentencesPage(navController: NavController) {
    var morseText by remember {mutableStateOf("")}
    var currentSentence by remember {mutableStateOf<String?>(null)}
    var result by remember {mutableStateOf<CheckResult?>(null)}

    val py = remember { Python.getInstance().getModule("model.morsecoder_sentences")}

    LaunchedEffect(Unit) {
        currentSentence = py.callAttr("random_word").toString()
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
            MainTitle("TRAINING")
            Spacer(Modifier.height(25.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                QuestionTable(currentSentence ?: "...")
                ResultBadge(
                    result = result,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                NormalTouchButton(
                    onClick = { morseText = py.callAttr("reveal_l2", currentSentence).toString() },
                    text = "I don't know!"
                )
            }
            Spacer(Modifier.height(20.dp))
            TextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF76b592),
                    unfocusedContainerColor = Color.White
                ),
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
            SpaceButton { morseText += " "  }
            Spacer(Modifier.height(10.dp))
            Row{
                CheckButton { val letter = currentSentence ?:return@CheckButton
                    val ok = py.callAttr("check_l3", letter, morseText).toBoolean()
                    result = if (ok) CheckResult.OK else CheckResult.WRONG
                    morseText = ""
                    if (ok){
                        currentSentence = py.callAttr("random_sentence").toString()
                        morseText = ""
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

        }

    }
}




