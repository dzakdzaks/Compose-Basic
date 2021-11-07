package com.dzakdzaks.composebasic.core

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Resource<T>(data = data)
    class Error<T>(message: String?, data: T? = null): Resource<T>(data = data, message = message)
    class Loading<T>(data: T? = null): Resource<T>(data = data)
}

suspend inline fun <reified T> HttpClient.getRequest(httpRequestBuilder: (HttpRequestBuilder) -> Unit): Resource<T> {
    return try {
        Resource.Success(data = get {
            httpRequestBuilder(this)
        })
    } catch (e: RedirectResponseException) {
        // 3xx - responses
        Resource.Error(message = e.response.status.description)
    } catch (e: ClientRequestException) {
        // 4xx - responses
        Resource.Error(message = e.response.status.description)
    } catch (e: ServerResponseException) {
        // 5xx - responses
        Resource.Error(message = e.response.status.description)
    } catch (e: Exception) {
        Resource.Error(message = e.message)
    }
}