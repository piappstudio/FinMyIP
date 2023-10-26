package com.piappstudio.finmyip.network

import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PiRepository @Inject constructor(private val iPApiConfig: iPApiConfig) {
    suspend fun fetchIpConfig() = flow {
        emit(Resource.loading())
        val result = iPApiConfig.fetchConfig()
        if (result.isSuccessful) {
            emit(Resource.success(result.body()))
        }else {
            emit(Resource.error(error = PIError(code=result.code(), message = "Something went wring")))
        }
    }
}