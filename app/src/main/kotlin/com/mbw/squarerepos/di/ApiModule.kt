package com.mbw.squarerepos.di

import com.mbw.squarerepos.network.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

/**
 * Module that provides dependencies related to API services.
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    /**
     * Provides the implementation of the ApiService interface.
     * 
     * @param retrofit The Retrofit instance used to create the service.
     * @return The ApiService implementation.
     */
    @Provides
    fun provideLoginService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}