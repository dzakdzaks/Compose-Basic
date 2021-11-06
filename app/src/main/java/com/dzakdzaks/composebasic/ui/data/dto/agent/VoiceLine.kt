package com.dzakdzaks.composebasic.ui.data.dto.agent


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceLine(
    @SerialName("maxDuration")
    val maxDuration: Double?,
    @SerialName("mediaList")
    val mediaList: List<Media>?,
    @SerialName("minDuration")
    val minDuration: Double?
)