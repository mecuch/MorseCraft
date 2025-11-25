package com.example.morsecraft.data

import android.Manifest
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.annotation.RequiresPermission
import java.io.File
import java.io.RandomAccessFile

class WavRecorder(
    private val sampleRate: Int = 16_000,
    private val audioSource: Int = MediaRecorder.AudioSource.MIC
) {
    @Volatile
    private var running = false
    private var rec: AudioRecord? = null
    private var io: RandomAccessFile? = null
    private var worker: Thread? = null

    val isRecording get() = running

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    fun start(output: File): Boolean {
        stop()

        val minBuf = AudioRecord.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )
        val bufSize = (minBuf * 2).coerceAtLeast(4096)

        rec = AudioRecord(
            audioSource,
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufSize
        )

        // >>> SPRAWDŹ INICJALIZACJĘ
        if (rec?.state != AudioRecord.STATE_INITIALIZED) {
            rec?.release(); rec = null
            return false
        }

        // Plik i nagłówek WAV dopiero po udanym init
        io = RandomAccessFile(output, "rw").apply {
            setLength(0)
            writeWavHeader(sampleRate, 1, 16, 0)
        }

        running = true
        try {
            rec!!.startRecording()
        } catch (e: IllegalStateException) {
            running = false
            rec?.release(); rec = null
            io?.close(); io = null
            return false
        }

        worker = Thread {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_AUDIO)
            val sBuf = ShortArray(bufSize / 2)
            var totalBytes = 0L
            try {
                while (running) {
                    val n = rec!!.read(sBuf, 0, sBuf.size)
                    if (n > 0) {
                        val bytes = ByteArray(n * 2)
                        var j = 0
                        for (i in 0 until n) {
                            val v = sBuf[i].toInt()
                            bytes[j++] = (v and 0xFF).toByte()
                            bytes[j++] = ((v ushr 8) and 0xFF).toByte()
                        }
                        io!!.write(bytes)
                        totalBytes += bytes.size
                    }
                }
            } finally {
                io?.finalizeWav(totalBytes)
                rec?.stop()
                rec?.release()
                rec = null
                io?.close()
                io = null
            }
        }.also { it.start() }

        return true
    }

    fun stop() {
        if (!running) return
        running = false
        try {
            worker?.join(500)
        } catch (_: InterruptedException) {
        }
        worker = null
    }

    // --- WAV helpers (RIFF PCM 16-bit LE) ---
    private fun RandomAccessFile.writeAscii(s: String) = write(s.toByteArray(Charsets.US_ASCII))
    private fun RandomAccessFile.writeIntLE(v: Int) {
        write(
            byteArrayOf(
                (v and 0xFF).toByte(),
                ((v shr 8) and 0xFF).toByte(),
                ((v shr 16) and 0xFF).toByte(),
                ((v shr 24) and 0xFF).toByte()
            )
        )
    }

    private fun RandomAccessFile.
            writeShortLE(v: Int) {
        write(
            byteArrayOf(
                (v and 0xFF).toByte(),
                ((v shr 8) and 0xFF).toByte()
            )
        )
    }

    private fun RandomAccessFile.writeWavHeader(
        sampleRate: Int,
        channels: Int,
        bitsPerSample: Int,
        dataLen: Int
    ) {
        val byteRate = sampleRate * channels * bitsPerSample / 8
        writeAscii("RIFF")
        writeIntLE(36 + dataLen)
        writeAscii("WAVE")
        writeAscii("fmt ")
        writeIntLE(16)                  // PCM subchunk size
        writeShortLE(1)                 // audio format = PCM
        writeShortLE(channels)
        writeIntLE(sampleRate)
        writeIntLE(byteRate)
        writeShortLE(channels * bitsPerSample / 8) // block align
        writeShortLE(bitsPerSample)
        writeAscii("data")
        writeIntLE(dataLen)
    }

    private fun RandomAccessFile.finalizeWav(dataLen: Long) {
        // uzupełnij rozmiary po nagraniu
        seek(4); writeIntLE((36 + dataLen).toInt())
        seek(40); writeIntLE(dataLen.toInt())
        seek(length()) // na wszelki
    }
}