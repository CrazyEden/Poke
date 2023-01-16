package com.example.composeapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.composeapplication.ui.model.ItemsListOneItemData
import com.example.composeapplication.ui.pokemonlistscreen.ErrorItem
import com.example.composeapplication.ui.pokemonlistscreen.SearchBar

@Composable
fun ItemsListScreen(
    hostController: NavHostController,
    vModel:ItemsLiseViewModel = hiltViewModel()
) {
    val filter = rememberSaveable() {
        mutableStateOf("")
    }
    val items = vModel.getPokeListPaging(filter = filter.value).collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()){
        SearchBar(
            onTextEdited = {
                filter.value = it
            }
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2)){
            items(items.itemCount){
                OneItem(itemsListOneItemData = items[it]!!){name->
                    hostController.navigate(route = "item_info/${name}",)
                }
            }
            if (items.loadState.append is LoadState.Loading){
                item(span = { GridItemSpan(2) }){
                    LoadItem()
                }
            }
            if (items.loadState.append is LoadState.Error
                || items.loadState.refresh is LoadState.Error)
                item (span = { GridItemSpan(2) }){
                    ErrorItem { items.refresh() }
                }
        }
    }
}
@Composable
fun LoadItem(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Composable
fun OneItem(
    itemsListOneItemData: ItemsListOneItemData,
    onItemClick: (name:String) -> Unit
) {
    Card(
        modifier = Modifier.padding(9.dp),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(itemsListOneItemData.nameForSearch) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(60.dp),
                model = itemsListOneItemData.imageUrl,
                contentDescription = ""
            )
            Text(modifier = Modifier.fillMaxWidth(),text = itemsListOneItemData.nameTitle)
        }
    }
}
