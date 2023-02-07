package com.example.domain.usecase

import com.example.domain.repo.ImagesRepositorySource

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 10:34 PM
 */

class GetImagesFromRemoteUseCase (private val imagesRepo: ImagesRepositorySource) {
    operator fun invoke() = imagesRepo.getImagesFromRemote()
}