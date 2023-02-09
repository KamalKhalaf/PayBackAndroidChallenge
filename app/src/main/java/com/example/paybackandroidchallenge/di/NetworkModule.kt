package com.example.paybackandroidchallenge.di

import com.example.paybackandroidchallenge.BuildConfig
import com.example.paybackandroidchallenge.common.ApiKeyInterceptor
import com.example.paybackandroidchallenge.common.ErrorInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 11:20 PM
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    @Singleton
    @Provides
    fun provideOkHttp(errorInterceptor: ErrorInterceptor, apiKeyInterceptor: ApiKeyInterceptor) : OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(errorInterceptor)
            addInterceptor(apiKeyInterceptor)
            addInterceptor(logger)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient) : Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(okHttp)
            baseUrl(BuildConfig.BASE_URL)
        }.build()
    }

    @Provides
    fun provideErrorInterceptor() : ErrorInterceptor {
        return ErrorInterceptor()
    }

    @Provides
    fun provideApiKeyInterceptor() : ApiKeyInterceptor {
        return ApiKeyInterceptor()
    }
}