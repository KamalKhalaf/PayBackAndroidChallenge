package com.example.paybackandroidchallenge.di

import com.example.cache.room.ImagesDao
import com.example.common.NetworkConnectivity
import com.example.data.remote.ServiceApi
import com.example.data.repo.DataRepositorySourceImpl
import com.example.domain.repo.DataRepositorySource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 11:54 PM
 */

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideServiceApi(retrofit: Retrofit) : ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDataRepository(serviceApi: ServiceApi, networkConnectivity: NetworkConnectivity, imagesDao: ImagesDao) : DataRepositorySource {
        return DataRepositorySourceImpl(serviceApi, networkConnectivity, imagesDao)
    }
}