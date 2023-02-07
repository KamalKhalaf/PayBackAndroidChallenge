package com.example.domain.repo

import com.example.domain.entity.ImagesPixabayList

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 10:33 PM
 */


interface DataRepositorySource {
    fun getImagesFromRemote(): ImagesPixabayList
    fun getImagesFromLocalStorage(): ImagesPixabayList
}