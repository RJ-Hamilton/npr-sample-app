package com.hamilton.nprsampleapp.ui.models

import com.hamilton.services.listening.api.models.domain.HeadlineItem

data class HeadlineUiModel(
    val title: String,
    val imageUrl: String?,
    val headlineUrl: String
)

object HeadlineUiModelMapper {
    fun fromHeadlineItem(headlineItem: HeadlineItem) = HeadlineUiModel(
        title = headlineItem.title,
        imageUrl = headlineItem.imageUrl,
        headlineUrl = headlineItem.url
    )
}
