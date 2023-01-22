package com.example.composeapplication.domain.model.onePokemonResponse


import com.google.gson.annotations.SerializedName

data class GameIndice(
    @SerializedName("game_index")
    val gameIndex: Int? = null,
    @SerializedName("version")
    val version: Version? = null
)