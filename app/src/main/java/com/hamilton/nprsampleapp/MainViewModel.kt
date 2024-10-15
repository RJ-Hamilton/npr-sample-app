package com.hamilton.nprsampleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamilton.nprsampleapp.ui.models.HeadlineUiModelMapper
import com.hamilton.services.listening.api.ListeningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val listeningRepository: ListeningRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    init {
        getHeadlines()
    }

    fun getHeadlines(isRefreshing: Boolean = false) {
        if (isRefreshing) {
            _uiState.update { currentState ->
                currentState.copy(isRefreshing = true)
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }
        }

        viewModelScope.launch(coroutineDispatcher) {
            try {
                val headlineItems = listeningRepository.getHeadlines()

                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        isRefreshing = false,
                        headlineUiModels = headlineItems.map { headlineItem ->
                            HeadlineUiModelMapper.fromHeadlineItem(headlineItem)
                        }
                    )
                }
            } catch (exception: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        isRefreshing = false,
                        errorState = ErrorState(
                            errorMessage = R.string.error_dialog_description
                        )
                    )
                }
            }
        }
    }

    fun dismissErrorMessage() {
        _uiState.update { currentState ->
            currentState.copy(
                errorState = null
            )
        }
    }

    fun setWebViewLoading(isLoading: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isWebViewLoading = isLoading)
        }
    }
}
