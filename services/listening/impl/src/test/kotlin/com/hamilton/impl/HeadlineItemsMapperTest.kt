package com.hamilton.impl

import com.hamilton.impl.models.testListeningResponse
import com.hamilton.services.listening.api.models.domain.HeadlineItem
import com.hamilton.services.listening.api.models.domain.HeadlineItemsMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class HeadlineItemsMapperTest {

    @Test
    fun `fromListeningResponse should correctly map ListeningResponse to HeadlineItem list`() {
        // GIVEN
        val listeningResponse = testListeningResponse

        // WHEN
        val result = HeadlineItemsMapper.fromListeningResponse(listeningResponse)

        // THEN
        val expected = listOf(
            HeadlineItem(
                title = "In his Florida primary, Matt Gaetz is fighting over the future identity of the GOP",
                imageUrl = "https://npr.brightspotcdn.com/dims3/default/strip/false/crop/1905x1905+592+0/resize/600/quality/100/format/jpeg/?url=http%3A%2F%2Fnpr-brightspot.s3.amazonaws.com%2Fe9%2Fdd%2Faf66655c4a339d506b32c60840e8%2Fimg-2352.jpg",
                url = "https://www.npr.org/2024/08/15/nx-s1-5073003/matt-gaetz-florida-primary-kevin-mccarthy"
            )
        )

        // Assert that the result matches the expected list
        assertEquals(expected, result)
    }

    @Test
    fun `fromListeningResponse should handle missing image or web links gracefully`() {
        // GIVEN
        val firstItem = testListeningResponse.items.first()
        val firstItemLinks = firstItem.links

        val itemMissingImage = firstItem.copy(
            links = firstItemLinks.copy(
                image = listOf(),
                web = firstItemLinks.web
            )
        )

        val itemMissingWebLink = firstItem.copy(
            links = firstItemLinks.copy(
                image = firstItemLinks.image,
                web = listOf()
            )
        )

        val listeningResponse = testListeningResponse.copy(
            items = listOf(
                itemMissingImage,
                itemMissingWebLink
            )
        )

        // WHEN
        val result = HeadlineItemsMapper.fromListeningResponse(listeningResponse)

        // THEN
        val expected = listOf(
            HeadlineItem(
                title = "In his Florida primary, Matt Gaetz is fighting over the future identity of the GOP",
                imageUrl = null,
                url = "https://www.npr.org/2024/08/15/nx-s1-5073003/matt-gaetz-florida-primary-kevin-mccarthy"
            ),
            HeadlineItem(
                title = "In his Florida primary, Matt Gaetz is fighting over the future identity of the GOP",
                imageUrl = "https://npr.brightspotcdn.com/dims3/default/strip/false/crop/1905x1905+592+0/resize/600/quality/100/format/jpeg/?url=http%3A%2F%2Fnpr-brightspot.s3.amazonaws.com%2Fe9%2Fdd%2Faf66655c4a339d506b32c60840e8%2Fimg-2352.jpg",
                url = ""
            )
        )

        assertEquals(expected, result)
    }
}
