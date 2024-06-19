package com.mbw.squarerepos.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mbw.squarerepos.base.constant.BASE_URL
import com.mbw.squarerepos.base.constant.TIMEOUT_CONNECT_SECONDS
import com.mbw.squarerepos.base.constant.TIMEOUT_READ_SECONDS
import com.mbw.squarerepos.base.constant.TIMEOUT_WRITE_SECONDS
import com.mbw.squarerepos.network.service.TokenInterceptor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger module to provide network related dependencies utilizing Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides a singleton Retrofit instance configured with an OkHttpClient and a Gson converter.
     * @param httpClient OkHttpClient instance for making HTTP requests.
     * @param gson Gson instance for JSON serialization/deserialization.
     * @return Retrofit instance for retrofit service creation.
     */
    @Singleton
    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    /**
     * Provides a Gson instance for JSON serialization/deserialization.
     * @return Gson instance.
     */
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    /**
     * Provides a singleton OkHttpClient instance customized with timeout settings.
     * @return OkHttpClient instance.
     */
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_CONNECT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_READ_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_WRITE_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(TokenInterceptor())
        .build()
}