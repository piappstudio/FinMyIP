package com.piappstudio.finmyip

import com.piappstudio.finmyip.network.IPDetail
import com.piappstudio.finmyip.network.PiRepository
import com.piappstudio.finmyip.network.Resource
import com.piappstudio.finmyip.network.iPApiConfig
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class PiRepositoryTest {

    @Test
    fun testFetchIpDetail() =  runBlocking{
        val ipApiConfig = mockk<iPApiConfig>(relaxed = true)
        coEvery { ipApiConfig.fetchConfig() } returns Response.success(IPDetail(ip = "127.0.0.1"))
        val repository = PiRepository(ipApiConfig)
       val result = repository.fetchIpConfig().take(2).toList()
        assertEquals(Resource.Status.LOADING, result[0].status)
        assertEquals(Resource.Status.SUCCESS, result[1].status)
        assertEquals("127.0.0.1", result[1].data?.ip)
    }

    @Test
    fun testFetchIpDetailError() =  runBlocking{
        val ipApiConfig = mockk<iPApiConfig>(relaxed = true)
        coEvery { ipApiConfig.fetchConfig() } returns Response.error(400, mockk(relaxed = true))
        val repository = PiRepository(ipApiConfig)
       val result = repository.fetchIpConfig().take(2).toList()
        assertEquals(Resource.Status.LOADING, result[0].status)
        assertEquals(Resource.Status.ERROR, result[1].status)
        assertEquals(400, result[1].error?.code)
    }


}