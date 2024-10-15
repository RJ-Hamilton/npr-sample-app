package com.hamilton.impl.models

import com.hamilton.services.listening.api.models.data.Attributes
import com.hamilton.services.listening.api.models.data.Image
import com.hamilton.services.listening.api.models.data.Item
import com.hamilton.services.listening.api.models.data.ItemAttributes
import com.hamilton.services.listening.api.models.data.ItemLinks
import com.hamilton.services.listening.api.models.data.Links
import com.hamilton.services.listening.api.models.data.ListeningResponse
import com.hamilton.services.listening.api.models.data.Slug
import com.hamilton.services.listening.api.models.data.Web

val testListeningResponse = ListeningResponse(
    attributes = Attributes(
        deepLinkId = "topHeadlines",
        id = "1f98d9c",
        itemType = "text",
        renderType = "more",
        title = "Top Headlines",
        type = "topHeadlines"
    ),
    href = "https://listening.api.npr.org/v2/modules/topHeadlines/module?hash=1f98d9c",
    items = listOf(
        Item(
            attributes = ItemAttributes(
                date = "2024-08-16T11:00:00+00:00",
                description = "Gaetz is in a primary fight tied to ousted House Speaker Kevin McCarthy, the latest salvo in an ongoing war. Gaetz led that ouster. Now his district encapsulates a GOP intraparty clash that fuels dysfunction on the House floor and the campaign trail.",
                provider = "NPR",
                renderType = "primary",
                slug = Slug(
                    externalId = "2",
                    href = "https://www.npr.org/programs/all-things-considered/",
                    title = "All Things Considered"
                ),
                storyId = "nx-s1-5073003",
                title = "In his Florida primary, Matt Gaetz is fighting over the future identity of the GOP",
                type = "text",
                uid = "nx-s1-5073003:TEXT"
            ),
            href = "congue",
            links = ItemLinks(
                audio = listOf(),
                image = listOf(
                    Image(
                        caption = " Rep. Matt Gaetz, R-Fla., addresses a crowd in Niceville, Fla. ",
                        contentType = "image/jpeg",
                        href = "https://npr.brightspotcdn.com/dims3/default/strip/false/crop/1905x1905+592+0/resize/600/quality/100/format/jpeg/?url=http%3A%2F%2Fnpr-brightspot.s3.amazonaws.com%2Fe9%2Fdd%2Faf66655c4a339d506b32c60840e8%2Fimg-2352.jpg",
                        image = "g-s1-17117",
                        producer = "Claudia Grisales",
                        provider = "NPR",
                        rel = "square"
                    )
                ),
                profile = listOf(),
                program = listOf(),
                up = listOf(),
                web = listOf(
                    Web(
                        contentType = "text/html",
                        href = "https://www.npr.org/2024/08/15/nx-s1-5073003/matt-gaetz-florida-primary-kevin-mccarthy",
                        linkText = null,
                        rel = null
                    )
                )
            ),
            version = "1.0"
        )
    ),
    links = Links(browse = listOf()),
    version = "1.0"
)
