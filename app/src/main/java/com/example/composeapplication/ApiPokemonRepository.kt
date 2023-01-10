package com.example.composeapplication

import com.example.composeapplication.onePokemonResponse.OnePokemonResponse

interface ApiPokemonRepository {
    suspend fun getOnePokemon(id:Int):OnePokemonResponse
}