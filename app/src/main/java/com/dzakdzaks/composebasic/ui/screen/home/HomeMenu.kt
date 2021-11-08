package com.dzakdzaks.composebasic.ui.screen.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.dzakdzaks.composebasic.R

sealed class HomeMenu(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object Agents : HomeMenu("agents", R.string.agents, Icons.Filled.Group)
    object Weapons : HomeMenu("weapons", R.string.weapons, Icons.Filled.Map)
    object GameModes : HomeMenu("gameModes", R.string.gameModes, Icons.Filled.VideogameAsset)
    object Maps : HomeMenu("maps", R.string.maps, Icons.Filled.Map)
    object CompetitiveTiers : HomeMenu("competitiveTier", R.string.competitiveTier, Icons.Filled.MilitaryTech)
    object Currencies : HomeMenu("currencies", R.string.currencies, Icons.Filled.Paid)
    object PlayerCards : HomeMenu("playerCards", R.string.playerCards, Icons.Filled.Style)

    companion object {
        fun generateHomeMenu(): List<HomeMenu> {
            return listOf(
                Agents,
                Weapons,
                GameModes,
                Maps,
                CompetitiveTiers,
                Currencies,
                PlayerCards,
            )
        }
    }
}