package com.hamilton.nprsampleapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hamilton.nprsampleapp.ui.models.HeadlineUiModel
import com.hamilton.nprsampleapp.ui.theme.NPRSampleAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Headline(
    modifier: Modifier = Modifier,
    headlineUiModel: HeadlineUiModel
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var showBottomSheet by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = sheetState.currentValue == SheetValue.Hidden) {
                showBottomSheet = true
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        CardContent(
            imageUrl = headlineUiModel.imageUrl,
            title = headlineUiModel.title
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier
                .fillMaxWidth()
                .safeDrawingPadding(),
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    showBottomSheet = false
                }
            },
            sheetState = sheetState,
            containerColor = Color.White,
            contentWindowInsets = {
                WindowInsets.navigationBars
            },
            properties = ModalBottomSheetDefaults.properties,
            content = {
                HeadlineWebView(
                    modifier = Modifier.fillMaxWidth(),
                    url = headlineUiModel.headlineUrl
                )
            }
        )
    }
}

@Composable
private fun CardContent(imageUrl: String?, title: String) {
    Row(
        modifier = Modifier.height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            model = imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(16.dp)
                .weight(2f),
            text = title
        )
    }
}

@PreviewLightDark
@Composable
private fun PreviewHeadline() {
    NPRSampleAppTheme {
        Headline(
            headlineUiModel = HeadlineUiModel(
                title = "This is a sample title",
                imageUrl = "",
                headlineUrl = ""
            )
        )
    }
}