package com.example.composeapplication.domain.model.onePokemonResponse


import com.google.gson.annotations.SerializedName

data class DreamWorld(
    @SerializedName("front_default")
    val frontDefault: String? = null,
    @SerializedName("front_female")
    val frontFemale: Any? = null
)