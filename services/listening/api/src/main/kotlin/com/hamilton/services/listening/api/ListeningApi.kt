package com.hamilton.services.listening.api

import com.hamilton.services.listening.api.models.data.ListeningResponse
import retrofit2.Response
import retrofit2.http.GET

interface ListeningApi {
    @GET("listening-api-response.json")
    suspend fun getFetchData(): Response<ListeningResponse>
}
