package com.example.data.repo

import com.example.cache.room.ImageModel
import com.example.cache.room.ImagesDao
import com.example.common.BaseResult
import com.example.common.NetworkConnectivity
import com.example.common.WrappedErrorResponse
import com.example.data.remote.ServiceApi
import com.example.domain.entity.Hit
import com.example.domain.entity.ImagesPixabayList
import com.example.domain.repo.DataRepositorySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow

/**
 * @Created by: Kamal.Farghali
 * @Date: 07/02/2023 : 11:08 PM
 */

class DataRepositorySourceImpl(
    private val serviceApi: ServiceApi,
    private val networkConnectivity: NetworkConnectivity,
    private val imagesDao: ImagesDao
) : DataRepositorySource {
    override suspend fun getImagesFromRemote(search: String): Flow<BaseResult<ImagesPixabayList, WrappedErrorResponse<ImagesPixabayList>>> {
        return flow {
            val response = serviceApi.getImages(search)
            emit(
                when {
                    !networkConnectivity.isConnected() -> {

                        var hits: List<Hit?>?
                        val dataFromCache: List<ImageModel> = imagesDao.getImages()
                        hits = imageModelToHit(dataFromCache)
                        val imagesPixabayList: ImagesPixabayList = ImagesPixabayList(hits)
                        BaseResult.Success(imagesPixabayList)
                    }
                    response.isSuccessful -> {
                        fetchImagesFromRemoteAndSaveToCache(response.body()!!)
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

    private fun hitToImageModel(hit: Hit): ImageModel {
        return ImageModel(
            hit.id,
            hit.comments,
            hit.downloads,
            hit.likes,
            hit.previewURL,
            hit.tags,
            hit.user
        )
    }

    private fun imageModelToHit(list: List<ImageModel>): List<Hit>? {
        var hits = mutableListOf<Hit>()
        list.forEachIndexed { _, element -> hits.add(toHit(element)) }
        return hits
    }

    private fun toHit(imageModel: ImageModel): Hit {
        var hit = Hit(
            id = imageModel.id,
            comments = imageModel.comments,
            downloads = imageModel.downloads,
            likes = imageModel.likes,
            previewURL = imageModel.previewURL,
            tags = imageModel.tags,
            user = imageModel.user,
        )

        return hit
    }

    override suspend fun getImagesFromLocalStorage(): Flow<BaseResult<ImagesPixabayList, WrappedErrorResponse<ImagesPixabayList>>> {
        TODO("dfsdff")
    }

    private suspend fun fetchImagesFromRemoteAndSaveToCache(response: ImagesPixabayList) {
        response.hits?.let { hitList ->
            if (hitList.isNotEmpty()) {
                for (i in hitList.indices) imagesDao.insertOrUpdate(hitToImageModel(hitList[i]!!))
            }
        }
    }
}