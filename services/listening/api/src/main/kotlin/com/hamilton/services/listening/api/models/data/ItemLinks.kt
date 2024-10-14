package com.hamilton.services.listening.api.models.data

import kotlinx.serialization.Serializable

@Serializable
data class ItemLinks(
    val audio: List<Audio>,
    val image: List<Image>,
    val profile: List<Profile>,
    val program: List<Program>,
    val up: List<Up>,
    val web: List<Web>
)