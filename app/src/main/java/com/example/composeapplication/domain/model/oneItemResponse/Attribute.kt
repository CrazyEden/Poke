package com.example.composeapplication.domain.model.oneItemResponse


import com.google.gson.annotations.SerializedName

data class Attribute(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)