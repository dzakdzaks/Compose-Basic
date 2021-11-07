package com.dzakdzaks.composebasic.module.agent.domain.usecase

import com.dzakdzaks.composebasic.core.Resource
import com.dzakdzaks.composebasic.module.agent.data.remote.dto.toAgents
import com.dzakdzaks.composebasic.module.agent.domain.model.Agent
import com.dzakdzaks.composebasic.module.agent.domain.repository.AgentRepository
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAgentsUseCase @Inject constructor(
    private val repository: AgentRepository
) {

    operator fun invoke(): Flow<Resource<List<Agent>>> = flow {
        try {
            emit(Resource.Loading<List<Agent>>())
            repository.getAgents().data.let { listDto ->
                if (listDto.isNullOrEmpty()) {
                    emit(Resource.Error<List<Agent>>(message = "There is no agent here."))
                } else {
                    emit(Resource.Success(listDto.map { it.toAgents() }))
                }
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            emit(Resource.Error<List<Agent>>(message = e.response.status.description))
        } catch (e: ClientRequestException) {
            // 4xx - responses
            emit(Resource.Error<List<Agent>>(message = e.response.status.description))
        } catch (e: ServerResponseException) {
            // 5xx - responses
            emit(Resource.Error<List<Agent>>(message = e.response.status.description))
        } catch (e: Exception) {
            emit(Resource.Error<List<Agent>>(message = e.message))
        }
    }

}