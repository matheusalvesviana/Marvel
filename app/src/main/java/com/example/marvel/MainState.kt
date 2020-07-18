package com.example.marvel

import com.example.marvel.model.Result

sealed class MainState {
    object Loading : MainState()
    data class Success(val result: MutableList<Result>) : MainState()
    data class Error(val exception: Throwable) : MainState()
}