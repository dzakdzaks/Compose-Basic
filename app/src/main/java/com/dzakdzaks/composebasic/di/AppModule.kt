package com.dzakdzaks.composebasic.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
            install(HttpTimeout) {
                connectTimeoutMillis = 5000
                requestTimeoutMillis = 5000
                socketTimeoutMillis = 5000
            }
        }
    }

}