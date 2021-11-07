package com.dzakdzaks.composebasic.core


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data<T>(
    @SerialName("data")
    val `data` : T?,
    @SerialName("status")
    val status: Int?
)