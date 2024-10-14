package com.hamilton.services.listening.api

import com.hamilton.services.listening.api.models.domain.HeadlineItem

interface ListeningRepository {

    /**
     * Suspended function to retrieve a list of `HeadlineItem` items.
     *
     * @return List<HeadlineItem> A list of `HeadlineItem` objects.
     */
    suspend fun getHeadlines(): List<HeadlineItem>
}