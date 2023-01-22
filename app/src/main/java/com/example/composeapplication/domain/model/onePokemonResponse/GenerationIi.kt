package com.example.composeapplication.domain.model.onePokemonResponse


import com.google.gson.annotations.SerializedName

data class GenerationIi(
    @SerializedName("crystal")
    val crystal: Crystal? = null,
    @SerializedName("gold")
    val gold: Gold? = null,
    @SerializedName("silver")
    val silver: Silver? = null
)