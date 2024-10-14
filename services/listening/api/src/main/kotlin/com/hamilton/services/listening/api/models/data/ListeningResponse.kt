package com.hamilton.services.listening.api.models.data

import kotlinx.serialization.Serializable

@Serializable
data class ListeningResponse(
    val attributes: Attributes,
    val href: String,
    val items: List<Item>,
    val links: Links,
    val version: String
)