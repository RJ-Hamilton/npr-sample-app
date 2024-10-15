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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.viewinterop.AndroidView
import com.hamilton.nprsampleapp.ui.theme.NPRSampleAppTheme

@Composable
fun HeadlineWebView(
    modifier: Modifier = Modifier,
    url: String
) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }

    if (isLoading) {
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
                        isLoading = true
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        isLoading = false
                    }
                }
                setBackgroundColor(Color.White.toArgb())
                loadUrl(url)
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun PreviewHeadlineWebView() {
    NPRSampleAppTheme {
        HeadlineWebView(url = "")
    }
}