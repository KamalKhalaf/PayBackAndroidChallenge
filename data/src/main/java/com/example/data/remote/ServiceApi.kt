package com.example.data.remote

import com.example.domain.entity.ImagesPixabayList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 10:50 PM
 */

interface ServiceApi {

    @GET("api")
    suspend fun getImages(@Query("q") search : String, @Query("key") apiKey : String = "33456474-eb6a56f63a9ae611faa1546d3", @Query("image_type") image_type : String = "photo"): Response<ImagesPixabayList>
}