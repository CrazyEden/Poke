package com.example.composeapplication.domain.model.oneItemResponse


import com.google.gson.annotations.SerializedName

data class FlavorTextEntry(
    @SerializedName("language")
    val language: Language? = Language(),
    @SerializedName("text")
    val text: String? = "",
    @SerializedName("version_group")
    val versionGroup: VersionGroup? = VersionGroup()
)