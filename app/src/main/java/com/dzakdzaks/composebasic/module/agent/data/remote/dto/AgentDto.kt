package com.dzakdzaks.composebasic.module.agent.data.remote.dto


import com.dzakdzaks.composebasic.module.agent.domain.model.Agent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgentDto(
    @SerialName("abilities")
    val abilities: List<AbilityDto>?,
    @SerialName("assetPath")
    val assetPath: String?,
    @SerialName("bustPortrait")
    val bustPortrait: String?,
    @SerialName("characterTags")
    val characterTags: List<String>?,
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
    val role: RoleDto?,
    @SerialName("uuid")
    val uuid: String?,
    @SerialName("voiceLine")
    val voiceLine: VoiceLineDto?
)

fun AgentDto.toAgents(): Agent {
    return Agent(
        uuid = uuid,
        description = description,
        abilities = abilities?.toAbilities(),
        assetPath = assetPath,
        bustPortrait = bustPortrait,
        characterTags = characterTags,
        developerName = developerName,
        isBaseContent = isBaseContent,
        displayIcon = displayIcon,
        displayIconSmall = displayIconSmall,
        displayName = displayName,
        fullPortrait = fullPortrait,
        isAvailableForTest = isAvailableForTest,
        isFullPortraitRightFacing = isFullPortraitRightFacing,
        isPlayableCharacter = isPlayableCharacter,
        killfeedPortrait = killfeedPortrait,
        role = role?.toRole(),
        voiceLine = voiceLine?.toVoiceLine(),
    )
}