package com.example.data.repo

import com.example.data.remote.ServiceApi
import com.example.domain.entity.ImagesPixabayList
import com.example.domain.repo.DataRepositorySource
import com.example.paybackandroidchallenge.common.BaseResult
import com.example.paybackandroidchallenge.common.NetworkConnectivity
import kotlinx.coroutines.flow.Flow

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 11:08 PM
 */


class DataRepositorySourceImpl (private val serviceApi: ServiceApi, private val networkConnectivity: NetworkConnectivity) : DataRepositorySource {
    override suspend fun getImagesFromRemote(): Flow<BaseResult<ImagesPixabayList, ImagesPixabayList>> {
        TODO("Not yet implemented")
    }

    override suspend fun getImagesFromLocalStorage(): Flow<BaseResult<ImagesPixabayList, ImagesPixabayList>> {
        TODO("Not yet implemented")
    }
}