package com.example.composeapplication.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ItemInfoScreen(
    itemInfoViewModel: ItemInfoViewModel = hiltViewModel(),
    vModel: ItemInfoViewModel,
    itemName: String
){
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = itemName)
    }
}