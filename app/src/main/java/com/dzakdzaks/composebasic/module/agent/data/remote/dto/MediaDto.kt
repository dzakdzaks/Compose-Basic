package com.dzakdzaks.composebasic.module.agent.data.remote.dto


import com.dzakdzaks.composebasic.module.agent.domain.model.Media
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaDto(
    @SerialName("id")
    val id: Int?,
    @SerialName("wave")
    val wave: String?,
    @SerialName("wwise")
    val wwise: String?
)

fun List<MediaDto>.toMedias(): List<Media> {
    val result: MutableList<Media> = mutableListOf()
    this.forEach {
        result.add(
            Media(
                id = it.id,
                wave = it.wave,
                wwise = it.wwise
            )
        )
    }
    return result
}