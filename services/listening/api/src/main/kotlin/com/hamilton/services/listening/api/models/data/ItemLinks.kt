package com.hamilton.services.listening.api.models.data

import kotlinx.serialization.Serializable

@Serializable
data class ItemLinks(
    val audio: List<Audio> = emptyList(),
    val image: List<Image>,
    val profile: List<Profile>,
    val program: List<Program> = emptyList(),
    val up: List<Up> = emptyList(),
    val web: List<Web>
)