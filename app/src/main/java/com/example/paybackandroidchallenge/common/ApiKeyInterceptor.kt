package com.example.paybackandroidchallenge.common

import com.example.paybackandroidchallenge.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

/**
 * @Created by: Kamal.Farghali
 * @Date: 09/02/2023 : 1:19 PM
 */

@Singleton
class ApiKeyInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            with(chain.request()) {
                newBuilder().url(
                    url.newBuilder()
                        .addQueryParameter("key", BuildConfig.API_KEY)
                        .build()
                ).build()
            }
        )
    }
}