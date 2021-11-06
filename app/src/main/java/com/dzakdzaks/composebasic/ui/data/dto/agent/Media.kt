package com.dzakdzaks.composebasic.ui.data.dto.agent


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    @SerialName("id")
    val id: Int?,
    @SerialName("wave")
    val wave: String?,
    @SerialName("wwise")
    val wwise: String?
)