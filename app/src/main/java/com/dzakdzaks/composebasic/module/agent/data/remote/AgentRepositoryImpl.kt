package com.dzakdzaks.composebasic.module.agent.data.remote

import com.dzakdzaks.composebasic.core.AgentsResponse
import com.dzakdzaks.composebasic.core.ApiRoute
import com.dzakdzaks.composebasic.module.agent.domain.repository.AgentRepository
import io.ktor.client.*
import io.ktor.client.request.*

class AgentRepositoryImpl(
    private val client: HttpClient
) : AgentRepository {

    override suspend fun getAgents(): AgentsResponse {
        return client.get {
            url(ApiRoute.AGENTS)
            parameter("isPlayableCharacter", true)
        }
    }

}