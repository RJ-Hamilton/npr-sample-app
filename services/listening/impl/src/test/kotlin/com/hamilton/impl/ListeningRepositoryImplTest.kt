package com.hamilton.impl

import com.hamilton.services.listening.api.ListeningApi
import com.hamilton.services.listening.api.models.data.ListeningResponse
import com.hamilton.services.listening.api.models.domain.HeadlineItem
import com.hamilton.services.listening.api.models.domain.HeadlineItemsMapper
import com.hamilton.services.listening.impl.ListeningRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.test.runTest
import org.junit.Before
import retrofit2.Response
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ListeningRepositoryImplTest {
    private val mockListeningApi: ListeningApi = mockk()
    private lateinit var repository: ListeningRepositoryImpl

    @Before
    fun setup() {
        repository = ListeningRepositoryImpl(listeningApi = mockListeningApi)
    }

    @Test
    fun `getHeadlines should return mapped headlines when API response is successful`() = runTest {
        // GIVEN
        val mockResponse = mockk<Response<ListeningResponse>>()
        val listeningResponse = mockk<ListeningResponse>()
        val expectedHeadlines = listOf(
            HeadlineItem("Title1", "Image1", "Url1"),
            HeadlineItem("Title2", "Image2", "Url2")
        )

        mockkObject(HeadlineItemsMapper)
        every { mockResponse.body() } returns listeningResponse
        coEvery { mockListeningApi.getFetchData() } returns mockResponse
        every { HeadlineItemsMapper.fromListeningResponse(listeningResponse) } returns expectedHeadlines

        // WHEN
        val result = repository.getHeadlines()

        // THEN
        assertEquals(expectedHeadlines, result)
        coVerify { mockListeningApi.getFetchData() }
    }

    @Test
    fun `getHeadlines should throw RuntimeException when API response body is null`() = runTest {
        // GIVEN
        val mockResponse = mockk<Response<ListeningResponse>>()

        every { mockResponse.body() } returns null
        coEvery { mockListeningApi.getFetchData() } returns mockResponse

        // WHEN & THEN
        assertFailsWith<RuntimeException> {
            repository.getHeadlines()
        }
    }

    @Test
    fun `getHeadlines should throw exception when API call fails`() = runTest {
        // GIVEN
        coEvery { mockListeningApi.getFetchData() } throws RuntimeException("API error")

        // WHEN & THEN
        assertFailsWith<RuntimeException> {
            repository.getHeadlines()
        }
    }
}