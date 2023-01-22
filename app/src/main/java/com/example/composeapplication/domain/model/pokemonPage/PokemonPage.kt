package com.example.composeapplication.domain.model.pokemonPage


import com.google.gson.annotations.SerializedName

data class PokemonPage(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val listOfPokemon: List<ShortPokemonInfo> = listOf()
)