package com.example.composeapplication.domain.model.pokemonPage


import com.google.gson.annotations.SerializedName

data class ShortPokemonInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)