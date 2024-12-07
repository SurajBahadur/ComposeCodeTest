package com.example.demo.data.api

import okhttp3.ResponseBody
import retrofit2.http.GET

interface NetworkService {
    @GET("71054d22-7dcf-4709-9b15-34fbc88c23b5")
    suspend fun getMedicineData(): ResponseBody
}