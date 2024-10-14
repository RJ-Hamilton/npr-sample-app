package com.hamilton.nprsampleapp

import androidx.annotation.StringRes
import com.hamilton.nprsampleapp.ui.models.HeadlineUiModel

data class State(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val headlineUiModels: List<HeadlineUiModel> = emptyList(),
    val errorState: ErrorState? = null
)

data class ErrorState(
    @StringRes val errorMessage: Int
)