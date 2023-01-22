package com.example.composeapplication.domain.model.oneItemResponse


import com.google.gson.annotations.SerializedName

data class OneItemResponse(
    @SerializedName("attributes")
    val attributes: List<Attribute>? = listOf(),
    @SerializedName("baby_trigger_for")
    val babyTriggerFor: Any? = null,
    @SerializedName("category")
    val category: Category? = Category(),
    @SerializedName("cost")
    val cost: Int? = 0,
    @SerializedName("effect_entries")
    val effectEntries: List<EffectEntry>? = listOf(),
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>? = listOf(),
    @SerializedName("fling_effect")
    val flingEffect: Any? = null,
    @SerializedName("fling_power")
    val flingPower: Any? = null,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndice>? = listOf(),
    @SerializedName("held_by_pokemon")
    val heldByPokemon: List<Any>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("machines")
    val machines: List<Any>? = listOf(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("names")
    val names: List<Name>? = listOf(),
    @SerializedName("sprites")
    val sprites: Sprites? = Sprites()
)