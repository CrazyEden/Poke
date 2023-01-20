package com.example.composeapplication.domain.model.onePokemonResponse


import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world")
    val dreamWorld: DreamWorld? = null,
    @SerializedName("home")
    val home: Home? = null,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork? = null
)