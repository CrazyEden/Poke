package com.example.composeapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ItemsList(hostController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "ITEM LIST",Modifier.clickable { hostController.navigate("item_info") })
    }
}