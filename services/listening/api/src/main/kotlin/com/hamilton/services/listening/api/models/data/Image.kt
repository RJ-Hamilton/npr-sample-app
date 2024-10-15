package com.hamilton.services.listening.api.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val caption: String,
    @SerialName("content-type") val contentType: String,
    val href: String,
    val image: String,
    val producer: String? = null,
    val provider: String,
    val rel: String? = null
)