package com.hamilton.services.listening.api.models.data

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val browse: List<Browse>
)