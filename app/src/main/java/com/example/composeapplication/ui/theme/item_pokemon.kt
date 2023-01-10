package com.example.composeapplication.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.composeapplication.R

@Composable
fun ItemPokemon(name:String){
    Box(Modifier
        .fillMaxSize()
        .background(Color.Cyan)) {
        Image(painter = painterResource(id = R.drawable.img), contentDescription ="" )
        Text(text =name)
    }
}