package com.example.composeapplication

import javax.inject.Inject

class ApiPokemonRepositoryImpl @Inject constructor(
    private val apiPokemon: ApiPokemon
):ApiPokemonRepository {
    override suspend fun getOnePokemon(id:Int) = apiPokemon.getPokemon(id).body()!!
}