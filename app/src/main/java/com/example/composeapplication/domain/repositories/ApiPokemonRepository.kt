package com.example.composeapplication.domain.repositories

import com.example.composeapplication.domain.model.itemsPage.ItemsPage
import com.example.composeapplication.domain.model.oneItemResponse.OneItemResponse
import com.example.composeapplication.domain.model.onePokemonResponse.OnePokemonResponse
import com.example.composeapplication.domain.model.pokemonPage.PokemonPage
import com.example.composeapplication.util.Resource


interface ApiPokemonRepository {
    suspend fun getOnePokemon(id:Int): Resource<OnePokemonResponse>
    suspend fun getPokemonPage(limit:Int, offset:Int): Resource<PokemonPage>
    suspend fun getOneItem(name:String): Resource<OneItemResponse>
    suspend fun getItemsPage(limit:Int, offset:Int): Resource<ItemsPage>
}