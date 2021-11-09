package com.dzakdzaks.composebasic.ui.screen.home

import android.content.res.Configuration
import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.RatingBar
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.load
import com.dzakdzaks.composebasic.R
import com.dzakdzaks.composebasic.module.agent.domain.model.Agent
import com.dzakdzaks.composebasic.ui.theme.ComposeBasicTheme
import com.flaviofaria.kenburnsview.KenBurnsView
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

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
    BoxWithConstraints {
        Column {
            val pagerState = rememberPagerState(
                pageCount = places.size,
                initialOffscreenLimit = 2
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) { page ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            val pageOffset =
                                calculateCurrentOffsetForPage(page).absoluteValue

                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                        }
                        .padding(
                            start = 12.dp,
                            end = 12.dp,
                        ),
                    shape = RoundedCornerShape(24.dp),
                    backgroundColor = Color.Gray
                ) {
                    val place = places[page]
                    Box {
                        val customView = KenBurnsView(LocalContext.current).also { imageView ->
                            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                            imageView.load(place.url)
                        }
                        AndroidView(
                            factory = { customView },
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(Color(android.graphics.Color.parseColor("#80000000")))
                        ) {}

                        Column(
                            Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        ) {

                            Text(
                                text = place.title,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )

                            val ratingBar = RatingBar(
                                LocalContext.current, null, R.attr.ratingBarStyleSmall
                            ).apply {
                                rating = place.rating
                                progressDrawable.setColorFilter(
                                    android.graphics.Color.parseColor("#ff8800"),
                                    PorterDuff.Mode.SRC_ATOP
                                )
                            }
                            AndroidView(
                                factory = { ratingBar },
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Text(
                                text = place.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                activeColor = MaterialTheme.colorScheme.onPrimary
            )
//            LaunchedEffect(Unit) {
//                while (true) {
//                    yield()
//                    delay(2000)
//                    pagerState.animateScrollToPage(
//                        page = (pagerState.currentPage + 1) % (pagerState.pageCount),
//                        animationSpec = tween(600)
//                    )
//                }
//            }
        }
    }
}


private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}

@OptIn(ExperimentalPagerApi::class)
fun PagerScope.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage + currentPageOffset) - page
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
                text = agent.displayName.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
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