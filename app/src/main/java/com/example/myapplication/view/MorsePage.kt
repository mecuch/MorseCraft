package com.example.myapplication.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chaquo.python.Python

@Composable
fun MorsePage() {
    var morseText by remember {mutableStateOf("")}
    var currentLetter by remember {mutableStateOf<String?>(null)}
    var result by remember {mutableStateOf<String?>(null)}

    val py = remember { Python.getInstance().getModule("morsecoder")}

    LaunchedEffect(Unit) {
        currentLetter = py.callAttr("random_letter").toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Translate into Morse Code:")
        Text("Letter: ${currentLetter ?: "..."}")

        TextField(
            value = morseText,
            onValueChange = { morseText = it },
            label = { Text("Your Morse Code") },
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            Button(onClick = { morseText += "." }) {
                Text(".")
            }
            Button(onClick = { morseText += "-" }) {
                Text("_")
            }
        }

        Button(onClick = {
            val letter = currentLetter ?: return@Button
            val ok = py.callAttr("check_l1", letter, morseText).toBoolean()
            result = if (ok) "OK" else "WRONG! Try again!"
            morseText = ""
            if (ok){
                currentLetter = py.callAttr("random_letter").toString()
                morseText = ""
            }
        }) {
            Text("CHECK!")
        }
        if (result != null) {
            Text("Wynik: $result")
        }
    }
}
