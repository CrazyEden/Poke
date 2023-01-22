package com.example.composeapplication.domain.model.onePokemonResponse


import com.google.gson.annotations.SerializedName

data class VersionGroupDetail(
    @SerializedName("level_learned_at")
    val levelLearnedAt: Int? = null,
    @SerializedName("move_learn_method")
    val moveLearnMethod: MoveLearnMethod? = null,
    @SerializedName("version_group")
    val versionGroup: VersionGroup? = null
)