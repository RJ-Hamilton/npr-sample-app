package com.hamilton.services.listening.api.models.domain

import com.hamilton.services.listening.api.models.data.ListeningResponse

data class HeadlineItem(
    val title: String,
    val imageUrl: String? = null,
    val url: String
)

object HeadlineItemsMapper {
    fun fromListeningResponse(listeningResponse: ListeningResponse): List<HeadlineItem> {
        return listeningResponse.items.map { items ->
            HeadlineItem(
                title = items.attributes.title,
                imageUrl = items.itemLinks.image.find { it.rel == "square" }?.image,
                url = items.itemLinks.web.first().href
            )
        }
    }
}
