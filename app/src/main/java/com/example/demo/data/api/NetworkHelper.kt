package com.example.demo.data.api

import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface NetworkHelper {

    /**
     * Fetches a flow of FakeStoreData from the FakeStore API.
     *
     * @return A Flow emitting FakeStoreData
     */

    suspend fun getMedicineData(): Flow<ResponseBody>
}