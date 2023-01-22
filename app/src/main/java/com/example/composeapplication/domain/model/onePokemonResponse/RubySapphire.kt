package com.example.composeapplication.domain.model.onePokemonResponse


import com.google.gson.annotations.SerializedName

data class RubySapphire(
    @SerializedName("back_default")
    val backDefault: String? = null,
    @SerializedName("back_shiny")
    val backShiny: String? = null,
    @SerializedName("front_default")
    val frontDefault: String? = null,
    @SerializedName("front_shiny")
    val frontShiny: String? = null
)