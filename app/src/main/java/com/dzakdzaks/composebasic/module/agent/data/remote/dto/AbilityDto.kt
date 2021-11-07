package com.dzakdzaks.composebasic.module.agent.data.remote.dto


import com.dzakdzaks.composebasic.module.agent.domain.model.Ability
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityDto(
    @SerialName("description")
    val description: String?,
    @SerialName("displayIcon")
    val displayIcon: String?,
    @SerialName("displayName")
    val displayName: String?,
    @SerialName("slot")
    val slot: String?
)

fun List<AbilityDto>.toAbilities(): List<Ability> {
    val result: MutableList<Ability> = mutableListOf()
    this.forEach {
        result.add(
            Ability(
                slot = it.slot,
                displayName = it.displayName,
                description = it.description,
                displayIcon = it.displayIcon)
        )
    }
    return result
}