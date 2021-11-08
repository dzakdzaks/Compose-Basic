package com.dzakdzaks.composebasic.ui.screen.home

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzakdzaks.composebasic.R
import com.dzakdzaks.composebasic.module.agent.domain.model.Agent
import com.dzakdzaks.composebasic.ui.theme.ComposeBasicTheme
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
//    val state = viewModel.state.value
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        item {
            HorizontalPagerHome()
        }
        item {
            MenuHome(navController = navController, menus = HomeMenu.generateHomeMenu())
        }
//        items(items = state.data) { agent ->
//            Greeting(agent = agent)
//        }
//        item {
//            if (state.errorMessage.isNotBlank()) {
//                Text(text = state.errorMessage)
//            }
//        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterialApi
@Composable
private fun HorizontalPagerHome() {
    Column {
        val pagerState = rememberPagerState()
        HorizontalPager(
            count = 10,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                backgroundColor = MaterialTheme.colorScheme.primary,
                onClick = {

                }
            ) {
                GlideImage(
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                    imageModel = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Kelimutu_2008-08-08.jpg/800px-Kelimutu_2008-08-08.jpg",
                    circularReveal = CircularReveal(250)
                )
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            activeColor = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MenuHome(
    navController: NavController,
    menus: List<HomeMenu>
) {
    BoxWithConstraints {
        FlowRow {
            menus.forEach { menu ->
                Card(
                    modifier = Modifier
                        .width(this@BoxWithConstraints.maxWidth / 2)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .aspectRatio(1f),
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    onClick = {
                        navController.navigate(menu.route)
                    }
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier.size(48.dp),
                            imageVector = menu.icon,
                            contentDescription = stringResource(id = menu.title),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                        )
                        Text(
                            textAlign = TextAlign.Center,
                            text = stringResource(id = menu.title),
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Greeting(agent: Agent) {
    Card(
        backgroundColor = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        onClick = {

        }
    ) {
        CardContent(agent = agent)
    }
}

@Composable
private fun CardContent(agent: Agent) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(4.dp))
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = agent.displayName.toString(), style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (isExpanded) {
                Text(
                    text = agent.description.toString()
                )
            } else {
                Text(
                    text = agent.description.toString(),
                    maxLines = 2,
                )
            }
        }
        IconButton(onClick = {
            isExpanded = !isExpanded
        }) {
            Icon(
                imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (isExpanded) stringResource(id = R.string.show_less) else stringResource(
                    id = R.string.show_less
                )
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(
    showBackground = true, widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        HomeScreen(navController = NavController(LocalContext.current))
    }
}