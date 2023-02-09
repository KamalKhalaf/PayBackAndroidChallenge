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
    suspend fun getImages(@Query("q") search : String, @Query("image_type") image_type : String = "photo"): Response<ImagesPixabayList>
}