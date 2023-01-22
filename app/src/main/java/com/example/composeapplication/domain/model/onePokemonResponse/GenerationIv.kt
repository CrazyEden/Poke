package com.example.composeapplication.domain.model.onePokemonResponse


import com.google.gson.annotations.SerializedName

data class GenerationIv(
    @SerializedName("diamond-pearl")
    val diamondPearl: DiamondPearl? = null,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilver? = null,
    @SerializedName("platinum")
    val platinum: Platinum? = null
)