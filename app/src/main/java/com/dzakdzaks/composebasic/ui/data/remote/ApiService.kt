package com.dzakdzaks.composebasic.ui.data.remote

import com.dzakdzaks.composebasic.ui.data.dto.agent.Agent

interface ApiService {

    suspend fun getAgents(): List<Agent>
}