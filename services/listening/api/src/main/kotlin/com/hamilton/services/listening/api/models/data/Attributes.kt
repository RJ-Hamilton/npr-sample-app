package com.hamilton.services.listening.api.models.data

import kotlinx.serialization.Serializable

@Serializable
data class Attributes(
    val deepLinkId: String,
    val id: String,
    val itemType: String,
    val renderType: String,
    val title: String,
    val type: String
)