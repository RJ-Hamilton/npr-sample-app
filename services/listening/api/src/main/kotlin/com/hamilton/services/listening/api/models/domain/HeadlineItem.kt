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
                imageUrl = items.links.image.find { it.rel == "square" }?.href,
                url = items.links.web.first().href
            )
        }
    }
}
