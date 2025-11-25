package com.example.morsecraft.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.morsecraft.R
import com.example.morsecraft.view.CheckResult
import android.media.SoundPool
import androidx.compose.runtime.getValue

val doto = FontFamily(
    Font(R.font.doto_variable)
)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MorseDotButton(
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(4)
            .build()
    }
    var soundId by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        soundId = soundPool.load(context, R.raw.dot, 1)
    }
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = {
                    if (soundId != 0) {
                        soundPool.play(
                            soundId,
                            1.0f,   // leftVolume
                            1.0f,   // rightVolume
                            1,      // priority
                            0,      // loop
                            1.0f    // rate = 1.0 (normalna prędkość)
                        )
                    }
                    onClick()
                }
            )
            .size(150.dp)
            .border(
                color = Color(0xFF2FAC66),
                shape = RoundedCornerShape(16.dp),
                width = 2.dp,
            ),
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            DotImage()
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MorseDashButton(
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(4)
            .build()
    }
    var soundId by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        soundId = soundPool.load(context, R.raw.dash, 1)
    }
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = {
                    if (soundId != 0) {
                        soundPool.play(
                            soundId,
                            1.0f,   // leftVolume
                            1.0f,   // rightVolume
                            1,      // priority
                            0,      // loop
                            1.0f    // rate = 1.0 (normalna prędkość)
                        )
                    }
                    onClick()
                }
            )
            .size(150.dp)
            .border(
                color = Color(0xFF2FAC66),
                shape = RoundedCornerShape(16.dp),
                width = 2.dp,
            ),
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            DashImage()
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckButton(
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick,
            )
            .size(width = 250.dp, height = 90.dp)
            .border(
                color = Color(0xFF2FAC66),
                shape = RoundedCornerShape(16.dp),
                width = 2.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CheckLogo()
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeleteButton(
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick,
            )
            .size(width = 250.dp, height = 90.dp)
            .border(
                color = Color(0xFF2FAC66),
                shape = RoundedCornerShape(16.dp),
                width = 2.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            DeleteLogo()
        }

    }
}

@Composable
fun ResultBadge(result: CheckResult?, modifier: Modifier = Modifier) {
    if (result == null) return
    val resId = when (result) {
        CheckResult.OK -> R.drawable.ok
        CheckResult.WRONG -> R.drawable.wrong
    }
    Image(
        painter = painterResource(id = resId),
        contentDescription = if (result == CheckResult.OK) "OK" else "Wrong",
        modifier = modifier.size(64.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecButton(
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick
            )
            .size(70.dp)
            .border(
                color = Color(0xFF2FAC66),
                shape = RoundedCornerShape(16.dp),
                width = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            RecLogo()
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StopRecButton(
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick
            )
            .size(70.dp)
            .border(
                color = Color(0xFF2FAC66),
                shape = RoundedCornerShape(16.dp),
                width = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            StopRecLogo()
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayButton(
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick
            )
            .size(70.dp)
            .border(
                color = Color(0xFF2FAC66),
                shape = RoundedCornerShape(16.dp),
                width = 2.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            PlayLogo()
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StopButton(
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick
            )
            .size(70.dp)
            .border(
                color = Color(0xFF2FAC66),
                shape = RoundedCornerShape(16.dp),
                width = 2.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            StopLogo()
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DecodeButton(
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick
            )
            .size(width = 340.dp, height = 80.dp)
            .border(
                color = Color(0xFF2FAC66),
                shape = RoundedCornerShape(16.dp),
                width = 2.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            DecodeLogo()
        }

    }
}

@Composable
fun MainTitle(value: String) {
    Text(
        text = value,
        fontSize = 49.sp,
        fontFamily = doto,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF2FAC66)
    )
}

@Composable
fun SubMainTitle(value: String) {
    Text(
        text = value,
        fontSize = 15.sp,
        fontFamily = doto,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF2FAC66)
    )
}

@Composable
fun BigTitle(value: String) {
    Text(
        text = value,
        fontSize = 75.sp,
        fontFamily = doto,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF2FAC66)
    )
}

@Composable
fun BigTitleBlack(value: String) {
    Text(
        text = value,
        fontSize = 200.sp,
        fontFamily = doto,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF000000)
    )
}

@Composable
fun MediumTitle(value: String) {
    Text(
        text = value,
        fontSize = 26.sp,
        fontFamily = doto,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF2FAC66)
    )
}

@Composable
fun NormalTouchTitle(value: String) {
    Text(
        text = value,
        fontSize = 20.sp,
        fontFamily = doto,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
fun MenuNormalTouchTitle(value: String) {
    Text(
        text = value,
        fontSize = 29.sp,
        fontFamily = doto,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF2FAC66)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NormalTouchButton(
    onClick: () -> Unit,
    text: String
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick,
            ),
        color = Color(0xFF2FAC66),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp
    ) {
        NormalTouchTitle(text)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuNormalTouchButton(
    onClick: () -> Unit,
    text: String,
    ID: Int
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick,
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = ID),
                contentDescription = "encode menu",
                modifier = Modifier
                    .size(75.dp)
            )
            Spacer(Modifier.width(20.dp))
            MenuNormalTouchTitle(text)
        }

    }
}

@Composable
fun BackButton(
    onClick: () -> Unit
){
    TextButton(
        onClick = onClick
    ) {
        SubMainTitle("< Back")
    }
}

@Composable
fun QuestionTable(
    value: String
){
    Box(
        modifier = Modifier
            .border(width = 1.dp, color = Color(0xFF2FAC66))
            .padding(8.dp)
    )
    {
        BigTitle(value)
    }
}

@Composable
fun MessageTable(
    value: String
){
    Box(
        modifier = Modifier
            .border(width = 1.dp, color = Color(0xFF2FAC66))
            .padding(8.dp)
    )
    {
        MediumTitle(value)
    }
}

