package com.piappstudio.finmyip.network

import retrofit2.Response
import retrofit2.http.GET

interface iPApiConfig {
    @GET("json")
    suspend fun fetchConfig():Response<IPDetail>
}