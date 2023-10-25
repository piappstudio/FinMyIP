package com.piappstudio.finmyip.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.piappstudio.finmyip.network.iPApiConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private val BASE_URL = "https://ipapi.co/"


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule  {
    /** To create [OkHttpClient],Used to create retrofit instance. */
    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }


    /** To create retrofit object based on [okHttpClient] [baseUrl] and [gson] configuration */
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient).build()
    }


    /** Tell to Hilt, how to construct [IMoiConfig] object */
    @Provides
    fun provideIPiRetrofitApi(
        retrofit: Retrofit
    ): iPApiConfig = retrofit.create(iPApiConfig::class.java)

    /** To create [Gson] object, used to create retrofit instance */
    @Singleton
    @Provides
    fun providesGson() = GsonBuilder().setLenient().create()
}