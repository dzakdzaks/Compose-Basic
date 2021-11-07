package com.dzakdzaks.composebasic.di

import com.dzakdzaks.composebasic.module.agent.data.remote.AgentRepositoryImpl
import com.dzakdzaks.composebasic.module.agent.domain.repository.AgentRepository
import com.dzakdzaks.composebasic.module.agent.domain.usecase.GetAgentsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AgentModule {

    @Provides
    @Singleton
    fun provideAgentRepository(client: HttpClient): AgentRepository {
        return AgentRepositoryImpl(client)
    }

    @Provides
    @Singleton
    fun provideGetAgentsUseCase(repository: AgentRepository): GetAgentsUseCase {
        return GetAgentsUseCase(repository)
    }

}
