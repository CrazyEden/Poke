package com.example.composeapplication.domain.model.onePokemonResponse


import com.google.gson.annotations.SerializedName

data class HeldItem(
    @SerializedName("item")
    val item: Item? = Item(),
    @SerializedName("version_details")
    val versionDetails: List<VersionDetail>? = listOf()
)