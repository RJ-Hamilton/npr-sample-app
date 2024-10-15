package com.hamilton.nprsampleapp

import com.hamilton.nprsampleapp.ui.models.HeadlineUiModelMapper
import com.hamilton.services.listening.api.ListeningRepository
import com.hamilton.services.listening.api.models.domain.HeadlineItem
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel
    private val mockListeningRepository: ListeningRepository = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        coEvery {
            mockListeningRepository.getHeadlines()
        } returns emptyList()

        viewModel = MainViewModel(
            listeningRepository = mockListeningRepository,
            coroutineDispatcher = testDispatcher
        )
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `getHeadlines should update UI state with correct data`() = runTest {
        // GIVEN
        val expectedResponse = listOf(
            HeadlineItem(
                title = "title1",
                imageUrl = "image1",
                url = "url1"
            ),
            HeadlineItem(
                title = "title2",
                imageUrl = "image2",
                url = "url2"
            ),
            HeadlineItem(
                title = "title3",
                imageUrl = "image3",
                url = "url3"
            )
        )
        coEvery {
            mockListeningRepository.getHeadlines()
        } returns expectedResponse

        val testResults = mutableListOf<State>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.toList(testResults)
        }

        // WHEN
        viewModel.getHeadlines()

        // THEN
        assertEquals(3, testResults.size)
        assertEquals(testResults[0], State())
        assertEquals(testResults[1], State(isLoading = true))
        assertEquals(
            testResults[2],
            State(
                isLoading = false,
                headlineUiModels = expectedResponse.map { HeadlineUiModelMapper.fromHeadlineItem(it) }
            )
        )
    }

    @Test
    fun `given isRefreshing is true, getHeadlines should update UI state with correct data`() =
        runTest {
            // GIVEN
            val expectedResponse = listOf(
                HeadlineItem(
                    title = "title1",
                    imageUrl = "image1",
                    url = "url1"
                ),
                HeadlineItem(
                    title = "title2",
                    imageUrl = "image2",
                    url = "url2"
                ),
                HeadlineItem(
                    title = "title3",
                    imageUrl = "image3",
                    url = "url3"
                )
            )
            coEvery {
                mockListeningRepository.getHeadlines()
            } returns expectedResponse

            val testResults = mutableListOf<State>()

            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel.uiState.toList(testResults)
            }

            // WHEN
            viewModel.getHeadlines(isRefreshing = true)

            // THEN
            assertEquals(3, testResults.size)
            assertEquals(testResults[0], State())
            assertEquals(testResults[1], State(isRefreshing = true))
            assertEquals(
                testResults[2],
                State(
                    isLoading = false,
                    headlineUiModels = expectedResponse.map {
                        HeadlineUiModelMapper.fromHeadlineItem(
                            it
                        )
                    }
                )
            )
        }

    @Test
    fun `getHeadlines should update the error state when an error occurs`() = runTest {
        // GIVEN
        coEvery {
            mockListeningRepository.getHeadlines()
        } throws RuntimeException()

        val testResults = mutableListOf<State>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.toList(testResults)
        }

        // WHEN
        viewModel.getHeadlines()

        // THEN
        assertEquals(3, testResults.size)
        assertEquals(testResults[0], State())
        assertEquals(testResults[1], State(isLoading = true))
        assertEquals(
            testResults[2],
            State(
                isLoading = false,
                errorState = ErrorState(
                    title = R.string.error_dialog_title,
                    description = R.string.error_dialog_description
                )
            )
        )
    }

    @Test
    fun `dismissErrorMessage should update the UI correctly`() = runTest {
        // GIVEN
        val testResults = mutableListOf<State>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.toList(testResults)
        }

        // WHEN
        viewModel.dismissErrorMessage()

        // THEN
        assertEquals(1, testResults.size)
        assertEquals(testResults[0], State(errorState = null))
    }

    @Test
    fun `setWebViewLoading should update the UI correctly`() = runTest {
        // GIVEN
        val testResults = mutableListOf<State>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.toList(testResults)
        }

        // WHEN
        viewModel.setWebViewLoading(isLoading = true)

        // THEN
        assertEquals(1, testResults.size)
        assertEquals(testResults[0], State(isWebViewLoading = true))
    }
}