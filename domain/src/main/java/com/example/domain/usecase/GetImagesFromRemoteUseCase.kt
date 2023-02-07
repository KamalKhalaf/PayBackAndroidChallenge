package com.example.domain.usecase

import com.example.common.BaseResult
import com.example.common.WrappedErrorResponse
import com.example.domain.entity.ImagesPixabayList
import com.example.domain.repo.DataRepositorySource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 10:34 PM
 */

class GetImagesFromRemoteUseCase @Inject constructor(private val imagesRepo: DataRepositorySource) {
    suspend fun invoke(search : String) : Flow<BaseResult<ImagesPixabayList, WrappedErrorResponse<ImagesPixabayList>>> {
        return imagesRepo.getImagesFromRemote(search)
    }
}