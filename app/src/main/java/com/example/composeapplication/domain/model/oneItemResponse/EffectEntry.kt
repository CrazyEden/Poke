package com.example.composeapplication.domain.model.oneItemResponse


import com.google.gson.annotations.SerializedName

data class EffectEntry(
    @SerializedName("effect")
    val effect: String? = null,
    @SerializedName("language")
    val language: Language? = null,
    @SerializedName("short_effect")
    val shortEffect: String? = null
)