package com.dzakdzaks.composebasic.ui.screen.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dzakdzaks.composebasic.R
import com.dzakdzaks.composebasic.ui.screen.MainScreen
import com.dzakdzaks.composebasic.ui.screen.OtherScreen
import com.dzakdzaks.composebasic.ui.screen.about.AboutScreen
import com.dzakdzaks.composebasic.ui.screen.favorite.FavoriteScreen
import com.dzakdzaks.composebasic.ui.screen.home.HomeScreen
import com.dzakdzaks.composebasic.ui.screen.search.SearchScreen
import com.dzakdzaks.composebasic.ui.screen.showBottomBar
import com.dzakdzaks.composebasic.ui.screen.showTopBar
import com.dzakdzaks.composebasic.ui.theme.ComposeBasicTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                MyApp()
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
private fun MyApp() {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }

    BoxWithConstraints {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                if (showTopBar(currentRoute = currentRoute)) {
                    LargeTopAppBar(
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        title = { Text(stringResource(id = R.string.app_name)) },
                        navigationIcon = {
                            IconButton(onClick = { navController.navigate(OtherScreen.About.route) }) {
                                Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = "Info"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { navController.navigate(OtherScreen.Search.route) }) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search"
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                }
            },
            bottomBar = {
                if (showBottomBar(currentRoute = currentRoute)) {
                    MainNavigationBar(items = MainScreen.generateMainScreen(),
                        navController = navController, onItemClick = { screen ->
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        })
                }
            }
        ) { innerPadding ->
            val context = LocalContext.current
            AnimatedNavHost(
                navController = navController,
                startDestination = MainScreen.Home.route,
                Modifier.padding(innerPadding)
            ) {
                composable(MainScreen.Home.route, exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -constraints.maxWidth / 2 },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(300))
                }, popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { -constraints.maxWidth / 2 },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                }) {
                    HomeScreen(navController = navController)
                }
                composable(MainScreen.Favorite.route, exitTransition = {
                    if (currentRoute == MainScreen.Favorite.route) {
                        slideOutHorizontally(
                            targetOffsetX = { constraints.maxWidth / 2 },
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )
                        ) + fadeOut(animationSpec = tween(300))
                    } else {
                        slideOutHorizontally(
                            targetOffsetX = { -constraints.maxWidth / 2 },
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )
                        ) + fadeOut(animationSpec = tween(300))
                    }
                }, popEnterTransition = {
                    if (currentRoute == MainScreen.Favorite.route) {
                        slideInHorizontally(
                            initialOffsetX = { constraints.maxWidth / 2 },
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )
                        ) + fadeIn(animationSpec = tween(300))
                    } else {
                        slideInHorizontally(
                            initialOffsetX = { -constraints.maxWidth / 2 },
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )
                        ) + fadeIn(animationSpec = tween(300))
                    }
                }) {
                    FavoriteScreen(navController = navController) {
                        Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                    }
                }
                composable(OtherScreen.Search.route, enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { constraints.maxWidth / 2 },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                }, popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { constraints.maxWidth / 2 },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(300))
                }) {
                    SearchScreen(navController = navController)
                }
                composable(OtherScreen.About.route, enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { constraints.maxWidth / 2 },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                }, popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { constraints.maxWidth / 2 },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(300))
                }) {
                    AboutScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
private fun MainNavigationBar(
    items: List<MainScreen>,
    navController: NavController,
    onItemClick: (MainScreen) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        items.forEach { screen ->
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val selectedItem = screen.route == navBackStackEntry?.destination?.route
            NavigationBarItem(
                selected = selectedItem,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.background,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = ContentAlpha.medium),
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = ContentAlpha.medium),
                    indicatorColor = MaterialTheme.colorScheme.onPrimary
                ),
                icon = {
                    Icon(
                        imageVector = screen.icon, contentDescription = stringResource(
                            id = screen.title
                        )
                    )
                },
                label = {
                    Text(text = stringResource(id = screen.title))
                },
                onClick = { onItemClick(screen) }
            )
        }
    }
}