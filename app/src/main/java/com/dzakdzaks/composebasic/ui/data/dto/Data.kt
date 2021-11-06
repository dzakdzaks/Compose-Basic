package com.dzakdzaks.composebasic.ui.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data<T>(
    @SerialName("data")
    val `data` : List<T>?,
    @SerialName("status")
    val status: Int?
)