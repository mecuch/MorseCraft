package com.example.morsecraft.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            ),
        color = Color.Blue,
        tonalElevation = 4.dp,
        shadowElevation = 4.dp
    ) {
        Text(
            text = "Kliknij lub Przytrzymaj",
            modifier = Modifier.padding(12.dp),
            color = Color.White
        )
    }
}

@Composable
fun MainTitle(value: String) {
    Text(
        text = value,
        fontSize = 35.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF057de6)
    )
}

@Composable
fun WarningTitleBig(value: String) {
    Text(
        text = value,
        fontSize = 25.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        color = Color.Red
    )
}

