package com.dzakdzaks.composebasic.ui.screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.dzakdzaks.composebasic.R

sealed class MainScreen(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object Home : MainScreen("home", R.string.home, Icons.Filled.Home)
    object Favorite : MainScreen("favorite", R.string.favorite, Icons.Filled.Favorite)

    companion object {
        fun generateMainScreen(): List<MainScreen> = listOf(Home, Favorite)
    }
}

sealed class OtherScreen(val route: String) {
    object Search : OtherScreen("search")
    object About : OtherScreen("about")
}

fun showBottomBar(currentRoute: String?): Boolean {
    return currentRoute == MainScreen.Home.route || currentRoute == MainScreen.Favorite.route
}

fun showTopBar(currentRoute: String?): Boolean {
    return currentRoute == MainScreen.Home.route || currentRoute == MainScreen.Favorite.route
}