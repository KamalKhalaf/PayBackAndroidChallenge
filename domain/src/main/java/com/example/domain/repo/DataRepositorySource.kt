package com.example.domain.repo

import com.example.domain.entity.ImagesPixabayList
import com.example.paybackandroidchallenge.common.BaseResult
import kotlinx.coroutines.flow.Flow

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 10:33 PM
 */


interface DataRepositorySource {
    suspend fun getImagesFromRemote(): Flow<BaseResult<ImagesPixabayList, ImagesPixabayList>>
    suspend fun getImagesFromLocalStorage(): Flow<BaseResult<ImagesPixabayList, ImagesPixabayList>>
}