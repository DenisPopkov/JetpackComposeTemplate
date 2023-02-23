package com.compose.template.utills

sealed class ViewState {
    object Empty : ViewState()
    object Loading : ViewState()
    data class Success(val task: String) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}