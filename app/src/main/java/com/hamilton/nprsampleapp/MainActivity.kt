package com.hamilton.nprsampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.hamilton.nprsampleapp.ui.MainContent
import com.hamilton.nprsampleapp.ui.theme.NPRSampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NPRSampleAppTheme {
                window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
                window.navigationBarColor = MaterialTheme.colorScheme.primary.toArgb()

                Box(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .background(color = Color.LightGray)
                ) {
                    MainContent()
                }
            }
        }
    }
}