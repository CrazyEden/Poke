package com.example.composeapplication.domain.model.oneItemResponse


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("language")
    val language: Language? = Language(),
    @SerializedName("name")
    val name: String? = ""
)