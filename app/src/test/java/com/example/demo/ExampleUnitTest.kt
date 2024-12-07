package com.example.demo

import com.example.demo.data.api.NetworkService
import com.example.demo.data.local.db.AppDatabase
import com.example.demo.ui.home.HomeViewModel
import com.example.demo.ui.home.repository.HomeRepository
import com.example.demo.ui.home.uiState.UiState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testSuccessfulDataFetchAndProcessing() {
        // Mock dependencies
        val mockApiService = mockk<NetworkService>()
        val mockResponseBody = mockk<ResponseBody>()
        val expectedData = """
        {
            "problems": [
                {
                    "Diabetes": [
                        {
                            "medications": [
                                {
                                    "medicationsClasses": [
                                        {
                                            "className": [
                                                {
                                                    "associatedDrug": [
                                                        { "name": "Medicine 1", "dose": "10mg", "strength": "Tablet" }
                                                    ]
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    """

        coEvery { mockApiService.getMedicineData() } returns mockResponseBody
        coEvery { mockResponseBody.string() } returns expectedData

        val repository = HomeRepository(mockApiService, mockk(AppDatabase::class.toString()))
        val testDispatcher = StandardTestDispatcher()
        val testScope = TestScope(testDispatcher)
        Dispatchers.setMain(testDispatcher)
        val viewModel = HomeViewModel(repository)


        // Start collecting the flow
        viewModel.storeData.onEach {

            // Assert the collected data
            assertNotNull(it)
            assertEquals(1, it.size)
            assertEquals("Medicine 1", it[0].name)
            assertEquals("10mg", it[0].dose)
            assertEquals("Tablet", it[0].strength)
            assertEquals(UiState.StoreData, viewModel.uiState.value) // Verify state change

        }.launchIn(testScope)


        viewModel.fetchMedicineData()

        testScope.advanceUntilIdle()
        // Verify mocks
        coVerify { mockApiService.getMedicineData() }
        coVerify { mockResponseBody.string() }
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testEmptyResponseHandling() {
        val mockApiService = mockk<NetworkService>()
        val mockResponseBody = mockk<ResponseBody>()

        coEvery { mockApiService.getMedicineData() } returns mockResponseBody
        coEvery { mockResponseBody.string() } returns ""

        val repository = HomeRepository(mockApiService, mockk(AppDatabase::class.toString()))
        val testDispatcher = StandardTestDispatcher()
        val testScope = TestScope(testDispatcher)
        Dispatchers.setMain(testDispatcher)
        val viewModel = HomeViewModel(repository)

        viewModel.storeData.onEach {

            assertTrue(it.isEmpty())

        }.launchIn(testScope)

        viewModel.fetchMedicineData()
        testScope.advanceUntilIdle()
        // Verify mocks
        coVerify { mockApiService.getMedicineData() }
        coVerify { mockResponseBody.string() }
        Dispatchers.resetMain()
    }

}