@file:Suppress("RemoveRedundantQualifierName", "RemoveRedundantQualifierName",
    "RemoveRedundantQualifierName")

package com.example.composeapplication.domain.model.onePokemonResponse


import com.google.gson.annotations.SerializedName

@Suppress("RemoveRedundantQualifierName", "RemoveRedundantQualifierName",
    "RemoveRedundantQualifierName")
data class GenerationIi(
    @SerializedName("crystal")
    val crystal: com.example.composeapplication.domain.model.onePokemonResponse.Crystal,
    @SerializedName("gold")
    val gold: com.example.composeapplication.domain.model.onePokemonResponse.Gold,
    @SerializedName("silver")
    val silver: com.example.composeapplication.domain.model.onePokemonResponse.Silver
)