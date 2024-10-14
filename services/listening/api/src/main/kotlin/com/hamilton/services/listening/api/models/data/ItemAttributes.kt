package com.hamilton.services.listening.api.models.data

import kotlinx.serialization.Serializable

@Serializable
data class ItemAttributes(
    val date: String,
    val description: String,
    val provider: String,
    val renderType: String,
    val slug: Slug,
    val storyId: String,
    val title: String,
    val type: String,
    val uid: String
)