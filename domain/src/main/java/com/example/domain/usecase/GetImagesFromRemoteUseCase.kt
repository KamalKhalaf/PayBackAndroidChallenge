package com.example.domain.usecase

import com.example.common.BaseResult
import com.example.domain.entity.ImagesPixabayList
import com.example.domain.repo.DataRepositorySource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 10:34 PM
 */

class GetImagesFromRemoteUseCase @Inject constructor(private val imagesRepo: DataRepositorySource) {
    suspend fun invoke() : Flow<BaseResult<ImagesPixabayList, ImagesPixabayList>> {
        return imagesRepo.getImagesFromRemote()
    }
}