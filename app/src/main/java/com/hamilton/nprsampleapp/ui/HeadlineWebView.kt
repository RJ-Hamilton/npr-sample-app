package com.hamilton.nprsampleapp.ui

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamilton.nprsampleapp.MainViewModel

@Composable
fun HeadlineWebView(
    modifier: Modifier = Modifier,
    url: String
) {
    val viewModel: MainViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    if (state.isWebViewLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.inversePrimary
            )
        }
    }

    AndroidView(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(0)),
        factory = {
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        viewModel.setWebViewLoading(true)
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        viewModel.setWebViewLoading(false)
                    }
                }
                setBackgroundColor(Color.White.toArgb())
                loadUrl(url)
            }
        }
    )
}
