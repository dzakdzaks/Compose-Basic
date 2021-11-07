package com.dzakdzaks.composebasic.core

data class ListState<T>(
    val isLoading: Boolean = false,
    val data: List<T> = emptyList(),
    val errorMessage: String = ""
)