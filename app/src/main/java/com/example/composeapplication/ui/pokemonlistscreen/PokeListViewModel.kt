package com.example.composeapplication.ui.pokemonlistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.composeapplication.ui.model.PokemonListOneItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(
    private val pokeListCommunication: PokeListCommunication
):ViewModel() {

    fun getPokeListPagingFlow(filter: String): Flow<PagingData<PokemonListOneItemData>>
        = pokeListCommunication.getPagingFlow(viewModelScope,filter)

}