package com.dzakdzaks.composebasic.module.agent.domain.model

data class VoiceLine(
    val maxDuration: Double?,
    val mediaList: List<Media>?,
    val minDuration: Double?
)