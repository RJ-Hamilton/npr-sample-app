package com.hamilton.nprsampleapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hamilton.nprsampleapp.ui.models.HeadlineUiModel
import com.hamilton.nprsampleapp.ui.theme.NPRSampleAppTheme

@Composable
fun Headline(
    modifier: Modifier = Modifier,
    headlineUiModel: HeadlineUiModel,
    onClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(headlineUiModel.headlineUrl)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier.weight(1f),
                model = headlineUiModel.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(2f),
                text = headlineUiModel.title
            )
        }
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
            ),
            onClick = {}
        )
    }
}