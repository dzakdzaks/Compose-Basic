package com.dzakdzaks.composebasic.ui.screen.agents

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzakdzaks.composebasic.core.ListState
import com.dzakdzaks.composebasic.core.Resource
import com.dzakdzaks.composebasic.module.agent.domain.model.Agent
import com.dzakdzaks.composebasic.module.agent.domain.usecase.GetAgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListAgentViewModel @Inject constructor(
    private val getAgentsUseCase: GetAgentsUseCase
): ViewModel() {

    private val _state = mutableStateOf(ListState<Agent>())
    val state: State<ListState<Agent>> = _state

    init {
        getAgents()
    }

    private fun getAgents() {
        getAgentsUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = ListState(errorMessage = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = ListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = ListState(data = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

}