package com.dzakdzaks.composebasic.module.agent.data.remote.dto


import com.dzakdzaks.composebasic.module.agent.domain.model.VoiceLine
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceLineDto(
    @SerialName("maxDuration")
    val maxDuration: Double?,
    @SerialName("mediaList")
    val mediaList: List<MediaDto>?,
    @SerialName("minDuration")
    val minDuration: Double?
)

fun VoiceLineDto.toVoiceLine(): VoiceLine {
    return VoiceLine(
        maxDuration = maxDuration,
        minDuration = minDuration,
        mediaList = mediaList?.toMedias(),
    )
}