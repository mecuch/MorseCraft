package com.example.morsecraft.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.morsecraft.R

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
        color = Color(0xFF057de6),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "<graphic placeholder>",
                modifier = Modifier.size(140.dp),
                color = Color.White,
                fontFamily = doto
            )
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
        color = Color(0xFF057de6)
    )
}

@Composable
fun WarningTitleBig(value: String) {
    Text(
        text = value,
        fontSize = 25.sp,
        fontFamily = doto,
        fontWeight = FontWeight.Bold,
        color = Color.Red
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
        color = Color(0xFF057de6)
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
        color = Color(0xFF057de6),
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
            ),
        tonalElevation = 20.dp,
        shadowElevation = 4.dp
    ) {
        MenuNormalTouchTitle(text)
    }
}

