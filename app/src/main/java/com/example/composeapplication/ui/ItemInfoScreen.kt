package com.example.composeapplication.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.composeapplication.ui.model.ItemInfoData
import com.example.composeapplication.ui.pokemoninfoscreen.PokemonInfoScreenErrorState
import com.example.composeapplication.util.Resource

@Composable
fun ItemInfoScreen(
    vModel: ItemInfoViewModel = hiltViewModel(),
    itemName: String,
){
    val data = vModel.flow.collectAsState()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(15.dp)
        .verticalScroll(rememberScrollState())
        .offset(y = 50.dp)
        .padding(bottom = 50.dp)
    ){
        Log.d("xdd", "${data.value.data?.name} ${data.value.message}")
        when(data.value){
            is Resource.Success -> ItemInfoSuccessState(data.value.data!!)
            is Resource.Loading -> ItemInfoLoadingState()
            is Resource.Error ->{
                PokemonInfoScreenErrorState(data.value.message!!){
                    vModel.loadItemInfo(itemName)
                }
            }
        }
    }
}

@Composable
fun ItemInfoSuccessState(itemInfoData: ItemInfoData){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier.size(50.dp),
            model = itemInfoData.imageUrl,
            contentDescription = itemInfoData.name
        )
        Text(text = itemInfoData.name, style = TextStyle(fontSize = 18.sp))

        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth())
        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .border(1.dp, Color.Red, RoundedCornerShape(50))
                .padding(horizontal = 10.dp, vertical = 5.dp),
            text = itemInfoData.category,
            style = TextStyle(
                fontSize = 18.sp,
                shadow = Shadow(blurRadius = 1f),
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth())
        itemInfoData.effects.forEach {

            Text(text = it)
            Spacer(modifier = Modifier
                .height(20.dp)
                .fillMaxWidth())
        }
    }
}

@Composable
fun ItemInfoLoadingState(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}