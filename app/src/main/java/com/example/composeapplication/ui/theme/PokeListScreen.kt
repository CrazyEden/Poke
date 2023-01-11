package com.example.composeapplication.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.composeapplication.ui.PokemonListOneItemData

@Composable
fun PokeListScreen(
    navController: NavController,
    list:LazyPagingItems<PokemonListOneItemData>
){
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column (modifier = Modifier.fillMaxWidth()) {
            SearchBar(
                onTextEdited = {
                TODO()
                }
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ){
                items(list){item ->
                    PokeItem(item!!,navController)
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
fun PokeItem(
    item: PokemonListOneItemData,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable {
                navController.navigate("poke_info/${item.id}")
            },
        shape = CircleShape,
        elevation = 5.dp,
        border = BorderStroke(3.dp,Color.DarkGray)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            AsyncImage(
                modifier = Modifier.size(200.dp),
                model = item.imageUrl,
                placeholder = painterResource(com.example.composeapplication.R.drawable.pokeball_place_holder),
                contentDescription = item.name,

            )
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(2.dp)
                    .background(Color.LightGray, CircleShape)
                    .padding(4.dp)
                    .padding(horizontal = 10.dp),
                text = item.name,
            )
        }
    }
}

@Composable
fun SearchBar(
    onTextEdited: (String) -> Unit
){
    val text = remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, CircleShape)
                .background(Color.White, CircleShape),
            value = text.value,
            onValueChange = {
                text.value = it
                onTextEdited(it)
            },
            singleLine = true,
            textStyle = TextStyle(color = Color.Green),
            label = { Text(text = "Поиск") }
        )


    }
}