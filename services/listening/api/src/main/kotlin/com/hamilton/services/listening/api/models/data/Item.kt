package com.hamilton.services.listening.api.models.data

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val attributes: ItemAttributes,
    val href: String,
    val links: ItemLinks,
    val version: String
)