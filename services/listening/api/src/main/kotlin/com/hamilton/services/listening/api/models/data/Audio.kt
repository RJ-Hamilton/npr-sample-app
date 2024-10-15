package com.hamilton.services.listening.api.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Audio(
    @SerialName("content-type") val contentType: String,
    val href: String,
    val rel: String? = null
)