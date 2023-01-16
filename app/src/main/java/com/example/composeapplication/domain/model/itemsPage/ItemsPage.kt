package com.example.composeapplication.domain.model.itemsPage


import com.google.gson.annotations.SerializedName

data class ItemsPage(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Result?>?
)