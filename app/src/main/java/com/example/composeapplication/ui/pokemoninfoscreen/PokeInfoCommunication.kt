package com.example.composeapplication.ui.pokemoninfoscreen

import com.example.composeapplication.ui.model.PokemonInfoData
import com.example.composeapplication.util.Resource
import kotlinx.coroutines.flow.StateFlow

interface PokeInfoCommunication {

    fun showData(data: Resource<PokemonInfoData>)

    fun getStateFlow() : StateFlow<Resource<PokemonInfoData>>

}