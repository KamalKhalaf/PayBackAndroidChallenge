package com.example.domain.usecase

import com.example.domain.repo.DataRepositorySource
import javax.inject.Inject

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 10:39 PM
 */


class GetImagesFromLocalStorageUseCase @Inject constructor(private val imagesRepo: DataRepositorySource) {
    suspend fun invoke() = imagesRepo.getImagesFromLocalStorage()
}
