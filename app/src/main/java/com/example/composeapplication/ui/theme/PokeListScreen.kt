package com.example.composeapplication.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.composeapplication.ui.MainViewModel
import com.example.composeapplication.ui.PokemonListOneItemData

@Composable
fun PokeListScreen(
    navController: NavController,
    vModel: MainViewModel
){
    val pokeFilter = rememberSaveable {
        mutableStateOf("")
    }
    val list = vModel.pokemonPaging(pokeFilter.value).collectAsLazyPagingItems()
//    val a = rememberSaveable {
//        mutableStateOf(vModel.pokemonPaging(pokeFilter.value))
//    }
//    a.value.collectAsLazyPagingItems()
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column (modifier = Modifier.fillMaxWidth()) {
            SearchBar(onTextEdited = { pokeFilter.value = it })
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2)
            ){
                items(list.itemCount) {
                    PokeItem(item = list[it]!!, navController = navController)
                }

                if (list.loadState.append is LoadState.Error
                    || list.loadState.refresh is LoadState.Error)
                    item (span = { GridItemSpan(2) }){
                        ErrorItem { list.refresh() }
                    }
            }


        }
    }
}

@Composable
fun ErrorItem(
    onRetry: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(Color.Red),
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = { onRetry() }
        ){
            Text(
                textAlign = TextAlign.Center,
                text = "Обновить"
            )
        }
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
    val text = rememberSaveable {
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
                text.value = it.lowercase()
                onTextEdited(it.lowercase())
            },
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            label = { Text(text = "Поиск") }
        )
    }
}