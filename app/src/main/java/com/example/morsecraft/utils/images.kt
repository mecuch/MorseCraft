package com.example.morsecraft.utils


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.morsecraft.R

@Composable
fun Disclaimer(){
    Image(
        painter = painterResource(id = R.drawable.warning_discl),
        contentDescription = "warning",
        modifier = Modifier
            .size(440.dp)
    )
}

