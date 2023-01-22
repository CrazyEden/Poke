package com.example.composeapplication.domain.model.onePokemonResponse


import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("slot")
    val slot: Int? = 0,
    @SerializedName("type")
    val type: TypeX? = TypeX()
)