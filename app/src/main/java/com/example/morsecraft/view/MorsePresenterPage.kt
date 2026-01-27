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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import android.media.SoundPool
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import com.chaquo.python.Python
import com.example.morsecraft.R
import com.example.morsecraft.utils.BackButton
import com.example.morsecraft.utils.MainTitle
import com.example.morsecraft.utils.MediumTitle
import com.example.morsecraft.utils.NormalTouchButton
import com.example.morsecraft.utils.SmallTitle
import com.example.morsecraft.utils.SubMainTitle
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MorsePresenterPage(navController: NavController){
    var currentLetter by remember {mutableStateOf<String?>(null)}
    val context = LocalContext.current
    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(4)
            .build()
    }
    var inputText by remember { mutableStateOf("") }
    var dotSoundId by remember { mutableStateOf(0) }
    var dashSoundId by remember { mutableStateOf(0) }
    var playbackJob by remember { mutableStateOf<Job?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val py = remember { Python.getInstance().getModule("model.morsepresenter")}
    LaunchedEffect(Unit) {
        currentLetter = "<Type text\nto reveal code>"
        dotSoundId = soundPool.load(context, R.raw.dot, 1)
        dashSoundId = soundPool.load(context, R.raw.dash, 1)
    }
    DisposableEffect(Unit) {
        onDispose {
            playbackJob?.cancel()
            soundPool.release()
        }
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
            SmallTitle("Type and encode to see the code")
            Spacer(Modifier.height(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF76b592),
                        unfocusedContainerColor = Color.White
                    ),
                    value = inputText,
                    onValueChange = { inputText = it },
                    label = { SubMainTitle("Your text") },
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
                Spacer(Modifier.height(12.dp))
                NormalTouchButton(
                    onClick = {

                        val morse = py.callAttr("presenter", inputText).toString()
                        currentLetter = morse
                        playbackJob?.cancel()
                        playbackJob = coroutineScope.launch {
                            playMorseSequence(
                                morse = morse,
                                soundPool = soundPool,
                                dotSoundId = dotSoundId,
                                dashSoundId = dashSoundId
                            )
                        }
                    },"ENCODE"
            )

                Spacer(Modifier.height(20.dp))
                Box (
                    modifier = Modifier
                        .border(width = 1.dp, color = Color(0xFF2FAC66))
                        .padding(8.dp)
                ){
                    MediumTitle("Code: $currentLetter")
                }
            }
        }
    }
}
private suspend fun playMorseSequence(
    morse: String,
    soundPool: SoundPool,
    dotSoundId: Int,
    dashSoundId: Int,
    unitMs: Long = 120L,
) {
    val dotDuration = unitMs
    val dashDuration = unitMs * 3
    val symbolGap = unitMs
    val symbols = morse.filter { it == '.' || it == '_' || it == '-' }
    symbols.forEachIndexed { index, symbol ->
        when (symbol) {
            '.' -> if (dotSoundId != 0) {
                soundPool.play(dotSoundId, 1.0f, 1.0f, 1, 0, 1.0f)
                delay(dotDuration)
            }
            '_', '-' -> if (dashSoundId != 0) {
                soundPool.play(dashSoundId, 1.0f, 1.0f, 1, 0, 1.0f)
                delay(dashDuration)
            }
        }
        if (index < symbols.lastIndex) {
            delay(symbolGap)
        }
    }
}