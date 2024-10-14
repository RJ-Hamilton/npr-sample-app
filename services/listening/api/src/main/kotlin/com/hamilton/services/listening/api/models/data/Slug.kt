package com.hamilton.services.listening.api.models.data

import kotlinx.serialization.Serializable

@Serializable
data class Slug(
    val externalId: String,
    val href: String,
    val title: String
)