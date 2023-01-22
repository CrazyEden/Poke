package com.example.composeapplication.ui.pokemonlistscreen

import androidx.paging.PagingData
import com.example.composeapplication.ui.model.PokemonListOneItemData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface PokeListCommunication {

    fun getPagingFlow(scope: CoroutineScope,filter:String) : Flow<PagingData<PokemonListOneItemData>>

}