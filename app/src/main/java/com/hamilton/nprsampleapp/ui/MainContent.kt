package com.hamilton.nprsampleapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamilton.nprsampleapp.MainViewModel
import com.hamilton.nprsampleapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    val viewModel: MainViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.inversePrimary
            )
        }
    } else {
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = {
                viewModel.getHeadlines(isRefreshing = true)
            }
        ) {
            LazyColumn(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.background
                )
            ) {
                itemsIndexed(state.headlineUiModels) { index, headlineUiModel ->
                    val paddingValues = when (index) {
                        0 -> PaddingValues(all = 16.dp)
                        state.headlineUiModels.lastIndex -> PaddingValues(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 48.dp
                        )

                        else -> PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    }

                    Headline(
                        modifier = Modifier.padding(paddingValues),
                        headlineUiModel = headlineUiModel
                    )
                }
            }
        }
    }

    state.errorState?.let {
        AlertDialog(
            onDismissRequest = { viewModel.dismissErrorMessage() },
            title = {
                Text(text = stringResource(R.string.error_dialog_title))
            },
            text = {
                Text(text = stringResource(R.string.error_dialog_description))
            },
            confirmButton = {
                Button(onClick = { viewModel.getHeadlines() }) {
                    Text(text = stringResource(R.string.error_dialog_retry_button))
                }
            },
            dismissButton = {
                Button(onClick = { viewModel.dismissErrorMessage() }) {
                    Text(text = stringResource(R.string.error_dialog_dismiss_button))
                }
            }
        )
    }
}
