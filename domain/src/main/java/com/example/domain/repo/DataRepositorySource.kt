package com.example.domain.repo

import com.example.common.BaseResult
import com.example.common.WrappedErrorResponse
import com.example.domain.entity.ImagesPixabayList
import kotlinx.coroutines.flow.Flow

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 10:33 PM
 */


interface DataRepositorySource {
    suspend fun getImagesFromRemote(search : String): Flow<BaseResult<ImagesPixabayList, WrappedErrorResponse<ImagesPixabayList>>>
    suspend fun getImagesFromLocalStorage(): Flow<BaseResult<ImagesPixabayList, WrappedErrorResponse<ImagesPixabayList>>>
}