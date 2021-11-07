package com.dzakdzaks.composebasic.module.agent.data.remote.dto


import com.dzakdzaks.composebasic.module.agent.domain.model.Role
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoleDto(
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

fun RoleDto.toRole(): Role {
    return Role(
        uuid = uuid,
        displayName = displayName,
        description = description,
        displayIcon = displayIcon,
        assetPath = assetPath
    )
}