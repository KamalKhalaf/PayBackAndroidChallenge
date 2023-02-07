package com.example.domain.usecase

import com.example.domain.repo.DataRepositorySource

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 10:39 PM
 */


class GetImagesFromLocalStorageUseCase (private val imagesRepo: DataRepositorySource) {
    suspend fun invoke() = imagesRepo.getImagesFromLocalStorage()
}
