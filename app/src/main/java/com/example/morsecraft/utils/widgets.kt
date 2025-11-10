package com.example.morsecraft.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.morsecraft.R
import com.example.morsecraft.view.CheckResult

val doto = FontFamily(
    Font(R.font.doto_variable)
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MorseArmButton(
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick

            )
            .size(250.dp),
        color = Color(0xFF4785c5),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CodeLogo()
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
            .size(width = 90.dp, height = 250.dp),
        color = Color(0xFF4785c5),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
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
            .size(100.dp),
        color = Color(0xFF4785c5),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
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
fun PlayButton(
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick
            )
            .size(100.dp),
        color = Color(0xFF4785c5),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
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
            .size(100.dp),
        color = Color(0xFF4785c5),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
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
            .size(width = 340.dp, height = 80.dp),
        color = Color(0xFF4785c5),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
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
        fontSize = 30.sp,
        fontFamily = doto,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4785c5)
    )
}

@Composable
fun SubMainTitle(value: String) {
    Text(
        text = value,
        fontSize = 15.sp,
        fontFamily = doto,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4785c5)
    )
}

@Composable
fun BigTitle(value: String) {
    Text(
        text = value,
        fontSize = 75.sp,
        fontFamily = doto,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4785c5)
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
        fontSize = 24.sp,
        fontFamily = doto,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4785c5)
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
        color = Color(0xFF4785c5),
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
    text: String
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick,
            )
    ) {
        MenuNormalTouchTitle(text)
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
            .border(width = 1.dp, color = Color(0xFF4785c5))
            .padding(8.dp)
    )
    {
        BigTitle(value)
    }
}

