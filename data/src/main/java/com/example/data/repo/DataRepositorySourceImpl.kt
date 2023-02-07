package com.example.data.repo

import com.example.data.remote.ServiceApi
import com.example.domain.entity.ImagesPixabayList
import com.example.domain.repo.DataRepositorySource

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 11:08 PM
 */


class DataRepositorySourceImpl (private val serviceApi: ServiceApi) : DataRepositorySource {
    override fun getImagesFromRemote(): ImagesPixabayList {
        TODO("Not yet implemented")
    }

    override fun getImagesFromLocalStorage(): ImagesPixabayList {
        TODO("Not yet implemented")
    }
}