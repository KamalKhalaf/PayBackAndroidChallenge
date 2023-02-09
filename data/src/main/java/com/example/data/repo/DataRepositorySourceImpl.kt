package com.example.data.repo

import com.example.common.BaseResult
import com.example.common.NetworkConnectivity
import com.example.common.WrappedErrorResponse
import com.example.data.remote.ServiceApi
import com.example.domain.entity.ImagesPixabayList
import com.example.domain.repo.DataRepositorySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 11:08 PM
 */

class DataRepositorySourceImpl (private val serviceApi: ServiceApi, private val networkConnectivity: NetworkConnectivity) : DataRepositorySource {
    override suspend fun getImagesFromRemote(search : String): Flow<BaseResult<ImagesPixabayList, WrappedErrorResponse<ImagesPixabayList>>> {
        return flow {
            val response = serviceApi.getImages(search)
            emit(
                when {
                    !networkConnectivity.isConnected() -> {

                        // TODO Get the data from the local storage is there is or return error


                        BaseResult.Error(
                            WrappedErrorResponse(
                                status = "error",
                                statusCode = -1
                            )
                        )
                    }
                    response.isSuccessful -> {

                        //TODO Get the data from remote server and save it too the local storage
                        //fetchImagesFromRemoteAndSaveToCache(response.body()!!)
                        BaseResult.Success(response.body()!!)
                    }
                    else -> {
                        BaseResult.Error(
                            WrappedErrorResponse(
                                statusCode = response.code(),
                                status = "Error"
                            )
                        )
                    }
                }
            )
        }
    }

    override suspend fun getImagesFromLocalStorage(): Flow<BaseResult<ImagesPixabayList, WrappedErrorResponse<ImagesPixabayList>>> {
        TODO("Not yet implemented")
    }

    private suspend fun fetchImagesFromRemoteAndSaveToCache(response: ImagesPixabayList){
        //TODO Get the data from the local storage
    }
}