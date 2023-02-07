package com.example.domain.usecase

import com.example.domain.repo.DataRepositorySource

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 10:34 PM
 */

class GetImagesFromRemoteUseCase (private val imagesRepo: DataRepositorySource) {
    operator fun invoke() = imagesRepo.getImagesFromRemote()
}