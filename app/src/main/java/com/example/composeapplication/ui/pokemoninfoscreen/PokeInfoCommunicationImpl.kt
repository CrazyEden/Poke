package com.example.composeapplication.ui.pokemoninfoscreen

import com.example.composeapplication.ui.model.PokemonInfoData
import com.example.composeapplication.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class PokeInfoCommunicationImpl : PokeInfoCommunication {
    private val flow = MutableStateFlow<Resource<PokemonInfoData>>(Resource.Loading())

    override fun showData(data: Resource<PokemonInfoData>) {
        flow.value = data
    }

    override fun getStateFlow() = flow.asStateFlow()

}