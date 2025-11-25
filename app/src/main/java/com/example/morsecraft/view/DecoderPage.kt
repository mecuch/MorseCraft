package com.example.morsecraft.view

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.chaquo.python.Python
import com.example.morsecraft.data.WavPlayer
import com.example.morsecraft.data.WavRecorder
import com.example.morsecraft.utils.BackButton
import com.example.morsecraft.utils.DecodeButton
import com.example.morsecraft.utils.MainTitle
import com.example.morsecraft.utils.MessageTable
import com.example.morsecraft.utils.MicroLogo
import com.example.morsecraft.utils.PlayButton
import com.example.morsecraft.utils.RecButton
import com.example.morsecraft.utils.StopButton
import com.example.morsecraft.utils.StopRecButton
import com.example.morsecraft.utils.SubMainTitle
import com.example.morsecraft.utils.routes
import java.io.File

@Composable
fun DecodePage(navController: NavController) {
    val ctx = LocalContext.current
    val recorder = remember { WavRecorder(16_000) }
    val player   = remember { WavPlayer() }
    val file = remember { File(ctx.cacheDir, "sample.wav") }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) recorder.start(file)
    }
    var isRecording by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    var msg by remember { mutableStateOf("idle") }
    var morsemsg by remember {mutableStateOf<String?>(null)}
    var alphabetmsg by remember {mutableStateOf<String?>(null)}
    val py = remember { Python.getInstance().getModule("model.morsewavdecoder")}
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
                RecButton {
                    if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.RECORD_AUDIO)
                        == PackageManager.PERMISSION_GRANTED
                    ) {
                        if (recorder.start(file)) {
                            isRecording = true
                            msg = "● recording…"
                        } else {
                            msg = "init failed (try 44100 Hz)"
                        }
                    } else launcher.launch(Manifest.permission.RECORD_AUDIO)
                }
                Spacer(Modifier.width(20.dp))
                StopRecButton { if (isRecording) {
                    recorder.stop()
                    isRecording = false
                    msg = if (file.exists()) "saved ${file.length()} B" else "no file"
                } else {
                    player.stop()
                }}
                Spacer(Modifier.width(20.dp))
                PlayButton { if (!isRecording && !isPlaying && file.exists() && file.length() > 44) {
                    isPlaying = true
                    msg = "playing…"
                    player.play(file) {
                        isPlaying = false
                        msg = "stopped playback"
                    }
                } else if (isPlaying) {
                    msg = "already playing"
                } else {
                    msg = "no playable WAV"
                }}
                Spacer(Modifier.width(20.dp))
                StopButton { if (isPlaying) {
                    player.stop()
                    isPlaying = false
                    msg = "stopped playback" }
                }
            }

            Spacer(Modifier.height(20.dp))
            SubMainTitle(msg)
            DecodeButton {
                val wavPath = file.absolutePath
                val decomorse = py.callAttr("getanswermorse", wavPath).toString()
                val decotext = py.callAttr("getanswertext", wavPath).toString()
                morsemsg = decomorse
                alphabetmsg = decotext
            }

            Spacer(Modifier.height(20.dp))
            MessageTable(morsemsg ?: "<Morse Code Message>")
            Spacer(Modifier.height(20.dp))
            MessageTable(alphabetmsg ?: "<Text Message>")

        }
    }



}
