package com.example.composeapplication.domain.model.itemsPage


import com.google.gson.annotations.SerializedName

data class ItemsPage(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val results: List<Result> = listOf()
)