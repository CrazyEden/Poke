package com.example.composeapplication.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.composeapplication.domain.model.pokemonPage.ShortPokemonInfo

@Composable
fun PokeListScreen(
    navController: NavController,
    list:LazyPagingItems<ShortPokemonInfo>
){
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column (modifier = Modifier.fillMaxWidth()) {
            SearchBar(callBack = {

            })
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(list){item ->
                    PokeItem(item!!)
                }
                if (list.loadState.refresh is LoadState.Error)
                    item{
                        ErrorItem()
                    }
            }
        }
    }
}

@Composable
fun ErrorItem() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(Color.Red)) {

    }
}

@Composable
fun PokeItem(item: ShortPokemonInfo) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(Color.LightGray, CircleShape)
            .padding(4.dp),
        text = "Имя: ${item.name}"
    )
}

@Composable
fun SearchBar(
    callBack: (String) -> Unit
){
    val text = remember {
        mutableStateOf("")
    }
    val isHintDisplayed = remember {
        mutableStateOf(true)
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(13.dp),
            value = text.value,
            onValueChange = {
                isHintDisplayed.value = it == ""
                text.value = it
                callBack(it)
            },
            singleLine = true,
            textStyle = TextStyle(color = Color.Green),
            label = {
                Text(text = "Поиск")
            }
        )


    }
}