package com.example.domain.usecase

import com.example.domain.entity.ImagesPixabayList
import com.example.domain.repo.DataRepositorySource
import com.example.paybackandroidchallenge.common.BaseResult
import kotlinx.coroutines.flow.Flow

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 10:34 PM
 */

class GetImagesFromRemoteUseCase (private val imagesRepo: DataRepositorySource) {
    suspend fun invoke() : Flow<BaseResult<ImagesPixabayList, ImagesPixabayList>> {
        return imagesRepo.getImagesFromRemote()
    }
}