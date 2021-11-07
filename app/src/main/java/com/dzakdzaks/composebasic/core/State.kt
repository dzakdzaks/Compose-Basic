package com.dzakdzaks.composebasic.core

data class State<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val errorMessage: String = ""
)