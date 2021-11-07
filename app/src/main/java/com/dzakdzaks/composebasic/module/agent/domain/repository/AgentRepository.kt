package com.dzakdzaks.composebasic.module.agent.domain.repository

import com.dzakdzaks.composebasic.core.AgentsResponse

interface AgentRepository {

    suspend fun getAgents(): AgentsResponse

}