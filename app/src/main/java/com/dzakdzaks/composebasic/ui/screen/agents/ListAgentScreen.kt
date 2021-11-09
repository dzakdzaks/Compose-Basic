package com.dzakdzaks.composebasic.ui.screen.agents

import android.widget.ImageView
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.load
import com.dzakdzaks.composebasic.core.findActivity
import com.dzakdzaks.composebasic.module.agent.domain.model.Agent
import com.dzakdzaks.composebasic.ui.screen.OtherScreen
import com.flaviofaria.kenburnsview.KenBurnsView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ListAgentScreen(
    navController: NavController,
    viewModel: ListAgentViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current
    if (!state.data.isNullOrEmpty()) {
        BoxWithConstraints {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
                val pagerState = rememberPagerState(
                    pageCount = state.data.size,
                    initialOffscreenLimit = 2
                )
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp),
                    activeColor = MaterialTheme.colorScheme.onPrimary
                )
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                ) { page ->
                    CardAgent(agent = state.data[page])
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CardAgent(agent: Agent) {
    val customView = KenBurnsView(LocalContext.current).also { imageView ->
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.load(agent.fullPortrait)
    }
    BoxWithConstraints {
        AndroidView(
            factory = { customView },
            modifier = Modifier.size(width = maxWidth, height = maxHeight / 2)
        )
    }
}