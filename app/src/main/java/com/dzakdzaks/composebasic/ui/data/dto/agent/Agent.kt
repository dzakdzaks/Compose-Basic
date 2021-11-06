package com.dzakdzaks.composebasic.ui.data.dto.agent


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Agent(
    @SerialName("abilities")
    val abilities: List<Ability>?,
    @SerialName("assetPath")
    val assetPath: String?,
    @SerialName("bustPortrait")
    val bustPortrait: String?,
    @SerialName("characterTags")
    val characterTags: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("developerName")
    val developerName: String?,
    @SerialName("displayIcon")
    val displayIcon: String?,
    @SerialName("displayIconSmall")
    val displayIconSmall: String?,
    @SerialName("displayName")
    val displayName: String?,
    @SerialName("fullPortrait")
    val fullPortrait: String?,
    @SerialName("isAvailableForTest")
    val isAvailableForTest: Boolean?,
    @SerialName("isBaseContent")
    val isBaseContent: Boolean?,
    @SerialName("isFullPortraitRightFacing")
    val isFullPortraitRightFacing: Boolean?,
    @SerialName("isPlayableCharacter")
    val isPlayableCharacter: Boolean?,
    @SerialName("killfeedPortrait")
    val killfeedPortrait: String?,
    @SerialName("role")
    val role: Role?,
    @SerialName("uuid")
    val uuid: String?,
    @SerialName("voiceLine")
    val voiceLine: VoiceLine?
)