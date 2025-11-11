package com.example.morsecraft.data

import android.media.MediaPlayer
import java.io.File

class WavPlayer {
    private var mp: MediaPlayer? = null
    val isPlaying get() = mp?.isPlaying == true

    fun play(file: File, onCompletion: (() -> Unit)? = null) {
        stop()
        mp = MediaPlayer().apply {
            setDataSource(file.absolutePath)
            setOnCompletionListener {
                onCompletion?.invoke()
                stop()
            }
            setOnPreparedListener { start() }
            prepareAsync()
        }
    }

    fun stop() {
        runCatching { mp?.stop() }; mp?.reset(); mp?.release(); mp = null
    }
}
