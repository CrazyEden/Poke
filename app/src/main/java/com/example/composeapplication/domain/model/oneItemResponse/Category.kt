package com.example.composeapplication.domain.model.oneItemResponse


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)