package com.example.myapplication.view
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DisclaimerPage(){
    Column {
        Text(
            text = "MorseCraft POC Edition"
        )
        Text(
            text = "Warning!"
        )
        Text(
            text = "This version is a proof of concept only!"
        )
        Button(onClick = {}) {
            Text(
                text = "I understand"
            )
        }
    }
}