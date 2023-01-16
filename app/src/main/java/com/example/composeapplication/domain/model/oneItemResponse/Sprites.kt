package com.example.composeapplication.domain.model.oneItemResponse


import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("default")
    val default: String?
)