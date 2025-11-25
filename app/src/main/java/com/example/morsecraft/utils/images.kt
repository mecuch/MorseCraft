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

@Composable
fun MenuLogo(){
    Image(
        painter = painterResource(id = R.drawable.logom),
        contentDescription = "menu logo",
        modifier = Modifier
            .size(240.dp)
    )
}

@Composable
fun MicroLogo(){
    Image(
        painter = painterResource(id = R.drawable.microphone),
        contentDescription = "menu logo",
        modifier = Modifier
            .size(60.dp)
    )
}

@Composable
fun RecLogo(){
    Image(
        painter = painterResource(id = R.drawable.rec_but),
        contentDescription = "rec logo",
        modifier = Modifier
            .size(100.dp)
    )
}

@Composable
fun StopRecLogo(){
    Image(
        painter = painterResource(id = R.drawable.stop_rec_butt),
        contentDescription = "stop rec logo",
        modifier = Modifier
            .size(100.dp)
    )
}

@Composable
fun PlayLogo(){
    Image(
        painter = painterResource(id = R.drawable.play_butt),
        contentDescription = "menu logo",
        modifier = Modifier
            .size(100.dp)
    )
}

@Composable
fun StopLogo(){
    Image(
        painter = painterResource(id = R.drawable.stop_butt),
        contentDescription = "menu logo",
        modifier = Modifier
            .size(100.dp)
    )
}

@Composable
fun DecodeLogo(){
    Image(
        painter = painterResource(id = R.drawable.decode),
        contentDescription = "menu logo",
        modifier = Modifier
            .size(340.dp)
    )
}

@Composable
fun CodeLogo(){
    Image(
        painter = painterResource(id = R.drawable.code),
        contentDescription = "code",
        modifier = Modifier
            .size(190.dp)
    )
}

@Composable
fun CheckLogo(){
    Image(
        painter = painterResource(id = R.drawable.check),
        contentDescription = "code",
        modifier = Modifier
            .size(140.dp)
    )
}

@Composable
fun DeleteLogo(){
    Image(
        painter = painterResource(id = R.drawable.delete),
        contentDescription = "delete",
        modifier = Modifier
            .size(100.dp)
    )
}

@Composable
fun DotImage(){
    Image(
        painter = painterResource(id = R.drawable.dott),
        contentDescription = "encode menu",
        modifier = Modifier
            .size(90.dp)
    )
}

@Composable
fun DashImage(){
    Image(
        painter = painterResource(id = R.drawable.dashh),
        contentDescription = "encode menu",
        modifier = Modifier
            .size(90.dp)
    )
}


