package com.dzakdzaks.composebasic.ui.data.dto.agent


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Role(
    @SerialName("assetPath")
    val assetPath: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("displayIcon")
    val displayIcon: String?,
    @SerialName("displayName")
    val displayName: String?,
    @SerialName("uuid")
    val uuid: String?
)