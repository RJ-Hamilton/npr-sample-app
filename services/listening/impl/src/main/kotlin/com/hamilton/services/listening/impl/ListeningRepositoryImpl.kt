package com.hamilton.services.listening.impl

import com.hamilton.services.listening.api.ListeningApi
import com.hamilton.services.listening.api.ListeningRepository
import com.hamilton.services.listening.api.models.domain.HeadlineItem
import com.hamilton.services.listening.api.models.domain.HeadlineItemsMapper

class ListeningRepositoryImpl(
    private val listeningApi: ListeningApi
) : ListeningRepository {
    override suspend fun getData(): List<HeadlineItem> {
        val response = listeningApi.getFetchData()

        return response.body()?.let { listeningResponse ->
            HeadlineItemsMapper.fromListeningResponse(listeningResponse)
        } ?: throw RuntimeException()
    }
}