package com.dzakdzaks.composebasic.ui.screen.agents

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dzakdzaks.composebasic.module.agent.domain.model.Agent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ListAgentScreen(
    viewModel: ListAgentViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val pagerState = rememberPagerState()
    BoxWithConstraints {
        Column {
            HorizontalPager(
                state = pagerState,
                count = state.data.size,
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)
            ) {
                CardAgent(agent = state.data[this.currentPage])
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
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CardAgent(agent: Agent) {
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
            imageModel = agent.fullPortrait
        )
    }
}