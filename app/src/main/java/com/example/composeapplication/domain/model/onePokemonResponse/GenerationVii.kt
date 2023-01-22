package com.example.composeapplication.domain.model.onePokemonResponse


import com.google.gson.annotations.SerializedName

data class GenerationVii(
    @SerializedName("icons")
    val icons: Icons? = null,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon? = null
)